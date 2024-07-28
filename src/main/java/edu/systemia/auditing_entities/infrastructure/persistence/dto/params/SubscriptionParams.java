package edu.systemia.auditing_entities.infrastructure.persistence.dto.params;

import org.springframework.web.bind.annotation.RequestParam;

public record SubscriptionParams(
	@RequestParam(required = true) Long authorID,
	@RequestParam(required = true) Long courseID
) {

}
