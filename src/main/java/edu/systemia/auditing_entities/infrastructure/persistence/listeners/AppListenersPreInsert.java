package edu.systemia.auditing_entities.infrastructure.persistence.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import java.util.Arrays;

@Slf4j
public class AppListenersPreInsert implements PreInsertEventListener {
	@Override
	public boolean onPreInsert(PreInsertEvent event) {

		log.info("state {}", Arrays.toString(event.getState()));

		return false;
	}
}
