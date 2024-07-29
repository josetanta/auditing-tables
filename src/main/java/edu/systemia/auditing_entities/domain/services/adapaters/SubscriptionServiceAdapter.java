package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.SubscriptionService;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.SubscriptionRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.views.SubscriptionView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceAdapter implements SubscriptionService {

	private final SubscriptionRepository repository;

	@Override
	public Page<SubscriptionView> findAllSubscription(@NonNull Pageable pageable, @NonNull Long id) {
		var subsPage = repository.findAllByAuthor_Id(pageable, id, SubscriptionView.class);
		return subsPage;
	}

}
