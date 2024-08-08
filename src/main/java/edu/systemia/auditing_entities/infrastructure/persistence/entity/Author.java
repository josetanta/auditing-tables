package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BLOG_AUTHORS")
@EntityListeners(AuditingEntityListener.class)
public class Author implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_SEQUENCE")
	@SequenceGenerator(name = "AUTHORS_SEQUENCE", sequenceName = "BLOG_AUTHOR_SEQ", allocationSize = 1)
	@Column(name = "AT_ID", precision = 19)
	private Long id;

	@Column(name = "AT_FIRSTNAME", length = 100)
	private String firstname;

	@Column(name = "AT_LASTNAME", length = 100)
	private String lastname;

	@Column(name = "AT_ACTIVE", length = 1, nullable = false)
	private String active;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(insertable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

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
