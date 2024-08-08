package edu.systemia.auditing_entities.infrastructure.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record NoteDTO(
	Long id,
	Integer notePartial,
	LocalDateTime createdAt,
	
	@JsonIgnore
	AuthorDTO author
	// Long authorID
) {

}
