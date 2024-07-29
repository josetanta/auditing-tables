package edu.systemia.auditing_entities.infrastructure.persistence.repository.views;

import java.time.LocalDateTime;

public interface SubscriptionView {

	Long getId();

	LocalDateTime getCreatedAt();

	AuthorView getAuthor();

	CourseView getCourse();

	interface AuthorView {

		/**
		 * Last name of Author
		 *
		 * @return Last name
		 */
		String getLastname();

		/**
		 * First name of Author
		 *
		 * @return First name
		 */
		String getFirstname();
	}

	interface CourseView {
		String getName();
	}
}
