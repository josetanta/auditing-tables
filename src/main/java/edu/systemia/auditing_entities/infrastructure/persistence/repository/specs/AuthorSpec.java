package edu.systemia.auditing_entities.infrastructure.persistence.repository.specs;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@UtilityClass
public class AuthorSpec {

	public static Specification<Author> withFirstname(String firstname) {
		return (root, query, cb) -> cb.and(List.of(
			cb.like(cb.upper(root.get("firstname")), ("%" + firstname + "%").toUpperCase())
		).toArray(new Predicate[0]));
	}

	public static Specification<Author> testJoins() {
		return (root, query, cb) -> {

			return cb.conjunction();
		};
	}

	public static Specification<Author> testJoins() {
		return (root, query, cb) -> {

			return cb.conjunction();
		};
	}

	public static Specification<Author> testJoins() {
		return (root, query, cb) -> {

			return cb.conjunction();
		};
	}
}
