package edu.systemia.auditing_entities;

import edu.systemia.auditing_entities.infrastructure.soap.SoapClient;
import edu.systemia.auditing_entities.infrastructure.wsdl.AddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

import java.util.Random;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@EnableJpaAuditing
@Slf4j
public class AuditingEntitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditingEntitiesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(SoapClient soapClient) {
		return args -> {
			var random = new Random();
			var val1 = random.nextInt(1, 100);
			var val2 = random.nextInt(1, 100);
			AddResponse addResponse = soapClient.getAddResponse(val1, val2);
			log.info("Result of sum: {} + {} = {}", val1, val2, addResponse.getAddResult());
		};
	}
}
