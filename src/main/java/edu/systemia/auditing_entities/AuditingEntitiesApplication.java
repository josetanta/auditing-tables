package edu.systemia.auditing_entities;

import edu.systemia.auditing_entities.infrastructure.soap.SoapClient;
import edu.systemia.auditing_entities.infrastructure.wsdl.AddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

import java.io.File;
import java.util.Properties;
import java.util.Random;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@EnableJpaAuditing
@Slf4j
public class AuditingEntitiesApplication extends SpringBootServletInitializer {


	private static final String LOCATION_PROPERTY = "spring.config.location";

	public static void main(String[] args) {

		var builder = new SpringApplicationBuilder(AuditingEntitiesApplication.class);
		configureApp(builder)
			.run(args);
	}

	// @Bean
	public CommandLineRunner commandLineRunner(SoapClient soapClient) {
		return args -> {
			var random = new Random();
			var val1 = random.nextInt(1, 100);
			var val2 = random.nextInt(1, 100);
			AddResponse addResponse = soapClient.getAddResponse(val1, val2);
			log.info("Result of sum: {} + {} = {}", val1, val2, addResponse.getAddResult());
		};
	}

	protected static SpringApplicationBuilder configureApp(SpringApplicationBuilder builder) {
		log.info(">>>>> LOAD CONFIGURATION {}", System.getProperty("conf"));
		var conf = System.getProperty("conf");
		var fsr = new FileSystemResource(conf);
		var path = fsr.getPath().concat(File.separator);
		Properties properties = new Properties();
		properties.setProperty(LOCATION_PROPERTY, "file:".concat(path));
		builder.properties(properties);
		return builder;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApp(builder);
	}
}
