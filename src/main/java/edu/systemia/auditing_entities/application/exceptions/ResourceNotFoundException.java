package edu.systemia.auditing_entities.application.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends EntityNotFoundException {

	private String language;
	private String keyTranslate;

	public ResourceNotFoundException(String message, String language, String keyTranslate) {
		super(message);
		this.language = language;
		this.keyTranslate = keyTranslate;
	}

	public ResourceNotFoundException(String message, String language) {
		super(message);
		this.language = language;
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
