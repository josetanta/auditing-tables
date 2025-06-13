package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
 * Persistence
 */
@Entity
@Table(name = "BLOG_AUTHORS")
@EntityListeners(AuditingEntityListener.class)
// @Cacheable

/*
 * Lombok
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author extends EntityBase implements Serializable {

	@Serial
	private static final long serialVersionUID = -3045399548540220677L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "AUTHORS_SEQUENCE")
	@SequenceGenerator(name = "AUTHORS_SEQUENCE", sequenceName = "BLOG_AUTHOR_SEQ", allocationSize = 1)
	@Column(name = "AT_ID", precision = 19)
	private Long id;

	@Column(name = "AT_FIRSTNAME", length = 100)
	private String firstname;

	@Column(name = "AT_LASTNAME", length = 100)
	private String lastname;

	@OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.PERSIST)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Subscription> subscriptions = new ArrayList<>();

	@OneToMany(mappedBy = "author", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Note> notes = new ArrayList<>();
}
