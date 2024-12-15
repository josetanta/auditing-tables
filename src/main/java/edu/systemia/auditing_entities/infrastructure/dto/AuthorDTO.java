package edu.systemia.auditing_entities.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;

public record AuthorDTO(
	Long id,
	String firstname,
	String lastname,
	Boolean active,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	List<SubscriptionDTO> subscriptions,

	@JsonManagedReference
	List<NoteDTO> notes
) {

}
