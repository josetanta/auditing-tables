package edu.systemia.auditing_entities.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	LocaleResolver localeResolver() {
		var session = new SessionLocaleResolver();
		session.setDefaultLocale(new Locale("es"));
		return session;
	}

	private LocaleChangeInterceptor localeChangeInterceptor() {
		var locale = new LocaleChangeInterceptor();
		locale.setParamName("lang");
		return locale;
	}
}
