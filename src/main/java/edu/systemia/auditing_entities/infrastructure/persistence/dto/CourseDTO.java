package edu.systemia.auditing_entities.infrastructure.persistence.dto;

import java.util.List;

public record CourseDTO(
	Long id,
	String name,
	List<SubscriptionDTO> subscriptions
) {

}
