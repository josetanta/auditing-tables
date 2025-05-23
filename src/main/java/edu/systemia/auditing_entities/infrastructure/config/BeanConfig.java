package edu.systemia.auditing_entities.infrastructure.config;

import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public CycleAvoidingMappingContext cycleAvoidingMappingContext() {
		return new CycleAvoidingMappingContext();
	}

}
