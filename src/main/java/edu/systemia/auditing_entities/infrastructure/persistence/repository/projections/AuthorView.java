package edu.systemia.auditing_entities.infrastructure.persistence.repository.projections;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;

import java.time.LocalDateTime;
import java.util.List;

/**
 * View secure for entity {@link Author}
 */
public interface AuthorView {

	/**
	 * Last name of Author
	 *
	 * @return Last name
	 */
	String getLastname();

	/**
	 * First name of Author
	 *
	 * @return First name
	 */
	String getFirstname();

	/**
	 * List of Note
	 *
	 * @return NoteView
	 */
	List<NoteView> getNotes();

	interface NoteView {
		Integer getNotePartial();

		LocalDateTime getCreatedAt();
	}
}
