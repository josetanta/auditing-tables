package edu.systemia.auditing_entities.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
