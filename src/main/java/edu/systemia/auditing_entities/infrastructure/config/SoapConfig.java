package edu.systemia.auditing_entities.infrastructure.config;

import edu.systemia.auditing_entities.infrastructure.soap.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jax = new Jaxb2Marshaller();
		jax.setContextPath("edu.systemia.auditing_entities.infrastructure.wsdl");
		return jax;
	}

	@Bean
	public SoapClient soapClient(Jaxb2Marshaller marshaller) {
		SoapClient client = new SoapClient();
		client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);

		return client;
	}
}
