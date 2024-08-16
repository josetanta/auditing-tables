package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.AuthorService;
import edu.systemia.auditing_entities.infrastructure.dto.AuthorQueryResult;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static edu.systemia.auditing_entities.infrastructure.persistence.repository.specs.AuthorSpec.withFirstname;

@Service
@RequiredArgsConstructor
public class AuthorServiceAdapter implements AuthorService {

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
				orders.add(order.isAscending() ? cb.asc(authorRoot.get(order.getProperty()))
						: order.isDescending() ? cb.desc(authorRoot.get(order.getProperty())) : null);
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

		return new PageImpl<>(resultList, pageable, resultList.size());
	}

	@Override
	public ByteArrayResource exportPdf() throws IOException, DocumentException {
		var classPathResource = new ClassPathResource("/static/images/oracle_logo.jpg");
		byte[] allBytes = Files.readAllBytes(classPathResource.getFile().toPath());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		
		Image image = Image.getInstance(allBytes);
		image.setBorder(1);
		image.setBorderColor(BaseColor.BLACK);
		
		var p1 = new Paragraph("JOSÉ GABRIEL TANTA CALDERÓN");
		p1.setAlignment(Element.ALIGN_RIGHT);
		
		var fontBold = new Font();
		fontBold.setStyle(Font.BOLDITALIC);
		fontBold.setColor(BaseColor.RED);
		
		var p2 = new Paragraph("JOSÉ GABRIEL TANTA CALDERÓN", fontBold);
		p2.setIndentationLeft(120);
		
		document.add(image);
		document.add(p1);
		document.add(p2);
		document.close();
		out.close();
		
		return new ByteArrayResource(out.toByteArray());
	}

}
