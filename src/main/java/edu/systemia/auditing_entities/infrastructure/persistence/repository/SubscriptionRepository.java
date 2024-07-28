package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.views.SubscriptionView;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Page<SubscriptionView> findAllByAuthor_Id(Pageable pageable, Long id);

    // <T> List<T> findAllByAuthor_Id(Long id, Class<T> type);
    <T> Page<T> findAllByAuthor_Id(Pageable pageable, Long id, Class<T> type);
}
