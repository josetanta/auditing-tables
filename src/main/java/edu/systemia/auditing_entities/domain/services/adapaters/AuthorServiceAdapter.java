package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.AuthorService;
import edu.systemia.auditing_entities.infrastructure.dto.AuthorQueryResult;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static edu.systemia.auditing_entities.infrastructure.persistence.repository.specs.AuthorSpec.withFirstname;

@Service
@RequiredArgsConstructor
public class AuthorServiceAdapter implements AuthorService {

	private final AuthorRepository authorRepository;

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	public Page<AuthorQueryResult> paginateAuthor(Pageable pageable, String filterFirstname) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuthorQueryResult> query = cb.createQuery(AuthorQueryResult.class);
		Root<Author> authorRoot = query.from(Author.class);

		var predicate = withFirstname(filterFirstname).toPredicate(authorRoot, query, cb);
		query.where(predicate);

		if (pageable.getSort().isSorted()) {
			var orders = new ArrayList<Order>();
			for (Sort.Order order : pageable.getSort()) {
				orders.add(order.isAscending() ? cb.asc(authorRoot.get(order.getProperty())) :
					order.isDescending() ? cb.desc(authorRoot.get(order.getProperty())) : null);
			}
			query.orderBy(orders);
		}

		query.select(cb.construct(AuthorQueryResult.class, authorRoot.get("id"), authorRoot.get("firstname")));

		var typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());
		var resultList = typedQuery.getResultList();


		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Author> countRoot = countQuery.from(Author.class);
		countQuery.select(cb.count(countRoot));
		countQuery.where(predicate);
		long count = entityManager.createQuery(countQuery).getSingleResult();

		return new PageImpl<>(resultList, pageable, resultList.size());
	}

}
