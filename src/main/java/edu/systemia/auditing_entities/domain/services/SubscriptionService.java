package edu.systemia.auditing_entities.domain.services;

import edu.systemia.auditing_entities.infrastructure.persistence.repository.views.SubscriptionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface SubscriptionService {

	/**
	 * Get all Subscription
	 *
	 * @param <T>      Page<SubscriptionDTO>
	 * @param pageable Pagination
	 * @param id       Identifier by Author
	 * @return Pagination for type <T>
	 */
	Page<SubscriptionView> findAllSubscription(@NonNull Pageable pageable, @NonNull Long id);

}
