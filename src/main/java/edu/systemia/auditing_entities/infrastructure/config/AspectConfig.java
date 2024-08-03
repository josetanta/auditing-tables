package edu.systemia.auditing_entities.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "edu.systemia.auditing_entities.application.*" })
public class AspectConfig {

}
