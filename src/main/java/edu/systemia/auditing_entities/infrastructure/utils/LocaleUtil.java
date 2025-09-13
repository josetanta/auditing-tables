package edu.systemia.auditing_entities.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LocaleUtil {

	private final MessageSource messageSource;

	public String getMessage(String code, @Nullable Object[] args) {
		try {
			var locale = LocaleContextHolder.getLocale();
			return messageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException ex) {
			log.warn("code not found '{}'", code);
			return "value_unknow";
		}
	}

	public String getMessage(String code) {
		return getMessage(code, null);
	}
}
