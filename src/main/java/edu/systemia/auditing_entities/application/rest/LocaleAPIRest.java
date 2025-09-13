package edu.systemia.auditing_entities.application.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/locale-resolver")
@RequiredArgsConstructor
public class LocaleAPIRest {

	private final MessageSource messageSource;

	@GetMapping
	public ResponseEntity<Map<String, String>> getTranslations(
		Locale locale
	) {
		var title = messageSource.getMessage("title", null, locale);
		var response = Map.of("message", title);
		return ResponseEntity.ok(response);
	}

}
