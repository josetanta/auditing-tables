package edu.systemia.auditing_entities.infrastructure.persistence.utils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

public final class AppUtils {

    public static <T> boolean verifyAllAttrsIfNotNull(T obj) {
	Field[] fields = obj.getClass().getDeclaredFields();
	return Stream.of(fields).allMatch(field -> {
	    field.setAccessible(true);
	    try {
		return !Objects.isNull(field.get(obj));
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		return false;
	    }
	});
    }
}
