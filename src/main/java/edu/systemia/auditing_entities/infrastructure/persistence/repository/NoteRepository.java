package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

	@EntityGraph(attributePaths = "author")
	Page<Note> getAllByAuthor_Id(Pageable pageable, long id);

}
