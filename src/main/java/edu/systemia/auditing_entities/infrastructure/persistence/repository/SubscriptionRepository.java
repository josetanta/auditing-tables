package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.views.SubscriptionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Page<SubscriptionView> findAllByAuthor_Id(Pageable pageable, Long id);

	// <T> List<T> findAllByAuthor_Id(Long id, Class<T> type);
	// <T> Page<T> findAllByAuthor_Id(Pageable pageable, Long id, Class<T> type);

	// @Query(value = "select s from Subscription s where s.author.id=:id")
	<T> Page<T> findAllByAuthor_Id(Pageable pageable, Long id, Class<T> type);

	// @Query(value = "select s from Subscription s where s.author.id=:id")
	// Page<SubscriptionView> findAllSubscriptions(Pageable pageable, Long id);
	// Page<SubscriptionView> getAllByAuthor_IdOrderByAuthor_FirstnameDesc(Pageable
	// pageable, Long autghorID);

	// Page<SubscriptionView> getAllByAuthor_IdOrderByCourse_NameDesc(Pageable pageable, Long autghorID);
	
	Page<SubscriptionView> getAllByAuthor_Id(Pageable pageable, Long autghorID);
}
