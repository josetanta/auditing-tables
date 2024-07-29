package edu.systemia.auditing_entities.infrastructure.persistence.dto;

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
	List<NoteDTO> notes
) {

}
