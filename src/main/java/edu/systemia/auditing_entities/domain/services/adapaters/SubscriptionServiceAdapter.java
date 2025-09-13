package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.SubscriptionService;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.SubscriptionRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.projections.SubscriptionView;
import edu.systemia.auditing_entities.infrastructure.utils.LocaleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceAdapter implements SubscriptionService {

	private final SubscriptionRepository repository;
	private final LocaleUtil localeUtil;

	@Override
	public Page<SubscriptionView> findAllSubscription(@NonNull Pageable pageable, @NonNull Long id) {
		// var subsPage = repository.findAllByAuthor_Id(pageable, id,
		// SubscriptionView.class);
		// var subsPage =
		// repository.getAllByAuthor_IdOrderByAuthor_FirstnameDesc(pageable, id);
		// var subsPage = repository.getAllByAuthor_IdOrderByCourse_NameDesc(pageable,
		// id);
		var subsPage = repository.getAllByAuthor_Id(pageable, id);
		var title = localeUtil.getMessage("title");
		var user = localeUtil.getMessage("user_local");
		log.info("Title '{}'", title);
		log.info("User '{}'", user);
		return subsPage;
	}

}
