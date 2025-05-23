package edu.systemia.auditing_entities.application.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ProblemDetail handleException(DataIntegrityViolationException ex) {
		log.error("Description error {}", ex.getMessage());
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "integrity constraint");
		problem.setType(URI.create("https://stackoverflow.com/questions/57324966/issue-with-pk-violation-on-insert"));
		problem.setTitle("database problem");
		return problem;
	}

}
