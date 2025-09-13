package edu.systemia.auditing_entities.domain.dto;

import java.util.List;

public record CourseDTO(
	Long id,
	String name,
	Boolean isPublished,
	List<SubscriptionDTO> subscriptions
) {

}
