package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BLOG_COURSES")
@Cacheable
public class Course implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SEQUENCE")
	@SequenceGenerator(name = "COURSES_SEQUENCE", sequenceName = "BLOG_COURSE_SEQ", allocationSize = 1)
	@Column(name = "CO_ID", precision = 19)
	private Long id;

	@Column(name = "CO_NAME", length = 100)
	private String name;

	@Column(name = "CO_PUBLISHIED", precision = 1)
	private Boolean isPublishied;
	
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "course", orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	Set<Subscription> subscriptions = new HashSet<>();
}
