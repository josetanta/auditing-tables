package edu.systemia.auditing_entities.application.advice;

import edu.systemia.auditing_entities.application.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public AppProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		log.error("Description DataIntegrityViolationException error {}", ex.getMessage());
		var problem = AppProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "integrity constraint");
		problem.setType(URI.create("https://stackoverflow.com/questions/57324966/issue-with-pk-violation-on-insert"));
		problem.setTitle("database problem");
		problem.setTitleStatus(HttpStatus.CONFLICT.name());
		problem.setLanguage("ES");
		return problem;
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public AppProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		log.error("Description MethodArgumentTypeMismatchException error {}", ex.getMessage());
		var problem = AppProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		problem.setType(URI.create("https://stackoverflow.com/questions/57324966/issue-with-pk-violation-on-insert"));
		problem.setTitle("bad Request");
		problem.setTitleStatus(HttpStatus.BAD_REQUEST.name());
		problem.setLanguage("ES");
		return problem;
	}


	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public AppProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("Description HttpMessageNotReadableException error {}", ex.getMessage());
		var problem = AppProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		problem.setType(URI.create("https://stackoverflow.com/questions/57324966/issue-with-pk-violation-on-insert"));
		problem.setTitle("bad Request");
		problem.setTitleStatus(HttpStatus.BAD_REQUEST.name());
		problem.setLanguage("ES");
		return problem;
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public AppProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("Description ResourceNotFoundException error {}", ex.getMessage());
		var problem = AppProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problem.setType(URI.create("https://stackoverflow.com/questions/57324966/issue-with-pk-violation-on-insert"));
		problem.setTitle("not found");
		problem.setTitleStatus(HttpStatus.NOT_FOUND.name());
		problem.setLanguage(ex.getLanguage());
		problem.setKeyTranslate(ex.getKeyTranslate());
		return problem;
	}

	@ExceptionHandler({ IOException.class })
	public AppProblemDetail handleIOException(IOException ex) {
		log.error("Description IOException error {}", ex.getMessage());
		var problem = AppProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problem.setTitle("not found");
		problem.setTitleStatus(HttpStatus.NOT_FOUND.name());
		problem.setLanguage(null);
		problem.setKeyTranslate("ERROR_FILE");
		return problem;
	}

}
