package edu.systemia.auditing_entities.infrastructure.persistence.listeners;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.EntityBase;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatusListener {

	@PrePersist
	public void beforePersist(EntityBase entityBase) {
		//		entityBase.setActivateAt(LocalDateTime.now());
		log.info("Date activation ON");
	}

	@PreUpdate
	public void beforeUpdate(EntityBase entityBase) {
		//		if (entityBase.getActive().equalsIgnoreCase("N")) {
		//			entityBase.setDeactivateAt(LocalDateTime.now());
		//		}
	}
}
