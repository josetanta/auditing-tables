package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = { MockitoExtension.class })
public class CourseAPIRestTest {

	@Mock
	private CourseRepository courseRepository;

	@InjectMocks
	private CourseAPIRest courseController;

	@Test
	void postCreateCourse_shouldSaveCourseAndReturnCreatedResponse() {
		// Arrange
		Course inputCourse = new Course();
		inputCourse.setName("Mathematics");

		Course savedCourse = new Course();
		savedCourse.setId(1L);
		savedCourse.setName("Mathematics");

		when(courseRepository.save(inputCourse)).thenReturn(savedCourse);

		// Act
		ResponseEntity<Course> response = courseController.postCreateCourse(inputCourse);

		// Assert
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().getLocation()); // URI is created (even if empty in the implementation)
		assertEquals(savedCourse, response.getBody());

		verify(courseRepository, times(1)).save(inputCourse);
	}

	@Test
	void postCreateCourse_shouldHandleNullInput() {
		// Act
		ResponseEntity<Course> response = courseController.postCreateCourse(null);

		// Assert
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(courseRepository, times(1)).save(any());
	}
}
