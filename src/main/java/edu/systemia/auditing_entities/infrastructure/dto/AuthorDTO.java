package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthorDTO {
	private Long id;
	private String firstname;
	private String lastname;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<SubscriptionDTO> subscriptions;

	@JsonManagedReference("author-notes")
	private List<NoteDTO> notes;
}
