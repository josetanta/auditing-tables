package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BLOG_COURSES")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "course", orphanRemoval = true)
	Set<Subscription> subscriptions;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SEQUENCE")
	@SequenceGenerator(name = "COURSES_SEQUENCE", sequenceName = "BLOG_COURSE_SEQ", allocationSize = 1)
	@Column(name = "CO_ID", precision = 19)
	private Long id;

	// @ManyToMany(mappedBy = "courses")
	// Set<Author> authors;
	@Column(name = "CO_NAME", length = 100)
	private String name;
}
