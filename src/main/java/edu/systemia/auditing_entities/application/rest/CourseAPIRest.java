package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.domain.services.SubscriptionService;
import edu.systemia.auditing_entities.infrastructure.dto.SubscriptionDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import edu.systemia.auditing_entities.infrastructure.persistence.mappers.SubscriptionMapper;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.CourseRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.SubscriptionRepository;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping(path = "courses")
@RequiredArgsConstructor
@Validated
public class CourseAPIRest {

	private final CourseRepository courseRepository;
	private final SubscriptionRepository subsRepository;
	private final SubscriptionService service;
	private final SubscriptionMapper subscriptionMapper;
	private final CycleAvoidingMappingContext context;

	@PostMapping
	public ResponseEntity<Course> postCreateCourse(@RequestBody Course course) {
		var courseSaved = courseRepository.save(course);
		return ResponseEntity.created(URI.create("")).body(courseSaved);
	}

	@GetMapping("{course-id}")
	public ResponseEntity<Course> getCourseByID(@PathVariable("course-id") Long id) {
		var course = courseRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(course);
	}

	@GetMapping
	public ResponseEntity<Page<Course>> getFindAllCourse(Pageable pageable) {
		log.info("Pagination by {}", pageable);

		var result = courseRepository.findAll(pageable);
		log.info("Result its size is {}", result.getSize());

		return ResponseEntity.ok(result);
	}

	@PostMapping(path = "/subscription")
	public ResponseEntity<Object> postRegisterSubscription(@RequestBody SubscriptionDTO dto) {
		var subs = Subscription.builder()
			.author(Author.builder().id(dto.authorId()).build())
			.course(Course.builder().id(dto.courseId()).build())
			.build();
		var saveSubs = subsRepository.save(subs);
		return ResponseEntity.created(URI.create("")).body(saveSubs.getId());
	}

	@GetMapping("/subscription")
	public ResponseEntity<Page<SubscriptionDTO>> getSubscription(Pageable pageable) {
		var result = subscriptionMapper.toDto(subsRepository.findAll(pageable), context);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/subscription-view")
	public ResponseEntity<Object> getPageSubscriptionView(Pageable pageable, @RequestParam Long id) {
		var result = service.findAllSubscription(pageable, id);
		return ResponseEntity.ok(result);
	}
}
