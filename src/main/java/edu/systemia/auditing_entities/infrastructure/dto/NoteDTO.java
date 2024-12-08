package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public record NoteDTO(
	Long id,
	Integer notePartial,
	LocalDateTime createdAt,
	
	@JsonIgnore
	AuthorDTO author
	// Long authorID
) {

}
