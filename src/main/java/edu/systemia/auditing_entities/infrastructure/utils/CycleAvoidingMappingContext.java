package edu.systemia.auditing_entities.infrastructure.utils;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Utility for avoid cycle mapping
 */
public class CycleAvoidingMappingContext {
	private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

	@BeforeMapping
	public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
		return targetType.cast(knownInstances.get(source));
		// return (T) knownInstances.get(source);
	}

	@BeforeMapping
	public void storeMappedInstance(Object source, @MappingTarget Object target) {
		knownInstances.put(source, target);
	}
}
