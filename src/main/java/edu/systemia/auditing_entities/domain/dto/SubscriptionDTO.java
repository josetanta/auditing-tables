package edu.systemia.auditing_entities.domain.dto;

import java.time.LocalDateTime;

public record SubscriptionDTO(
	Long id,
	LocalDateTime createdAt,
	Long authorId,
	Long courseId
) {

}
