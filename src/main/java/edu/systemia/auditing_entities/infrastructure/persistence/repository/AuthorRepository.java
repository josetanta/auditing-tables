package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.projections.AuthorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

	@Procedure(procedureName = "get_padded_sequence_value")
	String getProcedureSeqValue();

	Page<AuthorView> findAllByFirstnameContaining(Pageable pageable, String firstname);

	@Transactional
	@Modifying
	@Query("UPDATE Author a SET a.firstname = :firstname WHERE a.id = :id")
	void updateFirstname(Long id, String firstname);
}
