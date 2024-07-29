package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.domain.services.SubscriptionService;
import edu.systemia.auditing_entities.infrastructure.persistence.dto.SubscriptionDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.CourseRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.SubscriptionRepository;
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
	// private final SubscriptionMapper mapper;
	private final SubscriptionService service;

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

		// if (!verifyAllAttrsIfNotNull(params))
		// return ResponseEntity.badRequest().body("Error");

		// var subs = Subscription.builder()
		// .author(Author.builder().id(params.authorID()).build())
		// .course(Course.builder().id(params.courseID()).build()).build();
		// var subs = mapper.toSubscription(dto);
		// var saveSubs = subsRepository.save(subs);
		// return ResponseEntity.created(URI.create("")).body(saveSubs.getId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/subscription")
	public ResponseEntity<Page<Subscription>> getSubscription(Pageable pageable) {
		var result = subsRepository.findAll(pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/subscription-view")
	public ResponseEntity<Object> getPageSubscriptionView(Pageable pageable, @RequestParam Long id) {
		// var result = subsRepository.findAllByAuthor_Id(pageable, id);
		// var result = subsRepository.findAllByAuthor_Id(pageable, id, SubsSummary.class);
		// var result = subsRepository.findAllSubscriptions(pageable, id);
		var result = service.findAllSubscription(pageable, id);
		return ResponseEntity.ok(result);
	}
}
