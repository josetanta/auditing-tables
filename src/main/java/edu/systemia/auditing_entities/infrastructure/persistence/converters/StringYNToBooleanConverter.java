package edu.systemia.auditing_entities.infrastructure.persistence.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class StringYNToBooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return !Objects.isNull(attribute) ? (attribute ? "Y" : "N") : null;
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return !Objects.isNull(value) ? value.trim().equalsIgnoreCase("Y") : null;
	}
}
