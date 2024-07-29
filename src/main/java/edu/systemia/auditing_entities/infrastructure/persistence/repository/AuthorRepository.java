package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.views.AuthorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Procedure(procedureName = "get_padded_sequence_value")
	String getProcedureSeqValue();

	Page<AuthorView> findAllByFirstnameContaining(Pageable pageable, String firstname);
}
