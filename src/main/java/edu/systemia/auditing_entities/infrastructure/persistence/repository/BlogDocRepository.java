package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.BlogDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDocRepository extends JpaRepository<BlogDoc, Integer> {

}
