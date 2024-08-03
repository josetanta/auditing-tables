package edu.systemia.auditing_entities.infrastructure.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class UserVerifiedAspect {

	@Before("execution(* edu.systemia.auditing_entities.application.rest.*.*(..))")
	public void publicMethod() {
		log.info("BEFORE EXECUTE METHOD");
	}

}
