package edu.systemia.auditing_entities.infrastructure.persistence.mappers.converters;

import java.util.Objects;

public class BooleanYNMapper {
	public String asString(Boolean value) {
		return !Objects.isNull(value) ? (Boolean.TRUE.equals(value) ? "Y" : "N") : null;
	}

	public Boolean asBoolean(String value) {
		return !Objects.isNull(value) ? value.trim().endsWith("Y") : null;
	}
}
