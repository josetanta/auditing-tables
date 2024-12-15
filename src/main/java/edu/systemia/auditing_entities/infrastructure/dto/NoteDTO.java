package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

public record NoteDTO(
	Long id,
	Integer notePartial,
	LocalDateTime createdAt,

	@JsonBackReference
	AuthorDTO author
	// Long authorID
) {

}
