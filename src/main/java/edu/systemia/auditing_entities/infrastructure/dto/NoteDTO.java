package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class NoteDTO {
	private Long id;
	private Integer notePartial;
	private LocalDateTime createdAt;

	@JsonBackReference("author-notes")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private AuthorDTO author;
	// Long authorID
}
