package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
