package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDTO {
	private Long id;
	private Integer notePartial;
	private LocalDateTime createdAt;

	@JsonBackReference("author-notes")
	private AuthorDTO author;
	// Long authorID
}
