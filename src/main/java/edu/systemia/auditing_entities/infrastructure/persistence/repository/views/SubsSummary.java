package edu.systemia.auditing_entities.infrastructure.persistence.repository.views;

import java.time.LocalDateTime;

public interface SubsSummary {

	LocalDateTime getCreatedAt();

	AuthorSummary getAuthor();

	CourseSummary getCourse();

	interface AuthorSummary {
		String getFirstname();
	}

	interface CourseSummary {
		String getName();
	}
}
