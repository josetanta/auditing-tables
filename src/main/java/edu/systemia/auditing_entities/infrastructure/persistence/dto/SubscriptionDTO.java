package edu.systemia.auditing_entities.infrastructure.persistence.dto;

import java.time.LocalDateTime;

public record SubscriptionDTO(
	Long id,
	LocalDateTime createdAt,
	Long authorId,
	Long courseId
) {

}
