package edu.systemia.auditing_entities.infrastructure.persistence.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record NoteDTO(
	Long id,
	Integer notePartial,
	LocalDateTime createdAt,
	@JsonIgnore
	AuthorDTO author
) implements Serializable {

}
