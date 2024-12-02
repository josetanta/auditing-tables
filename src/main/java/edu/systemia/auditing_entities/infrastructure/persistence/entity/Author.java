package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "BLOG_AUTHORS")
@EntityListeners(AuditingEntityListener.class)
public class Author extends EntityBase implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_SEQUENCE")
	@SequenceGenerator(name = "AUTHORS_SEQUENCE", sequenceName = "BLOG_AUTHOR_SEQ", allocationSize = 1)
	@Column(name = "AT_ID", precision = 19)
	private Long id;

	@Column(name = "AT_FIRSTNAME", length = 100)
	private String firstname;

	@Column(name = "AT_LASTNAME", length = 100)
	private String lastname;

	// @ManyToMany
	// @JoinTable(name = "COURSE_AUTHORS",
	// joinColumns = @JoinColumn(name = "AT_ID"),
	// inverseJoinColumns = @JoinColumn(name = "CO_ID")
	// )
	// Set<Course> courses;
	@OneToMany(mappedBy = "author")
	@ToString.Exclude
	private List<Subscription> subscriptions;

	@OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<Note> notes;
}
