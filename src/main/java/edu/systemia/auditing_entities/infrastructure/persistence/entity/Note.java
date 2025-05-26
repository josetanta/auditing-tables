package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BLOG_NOTES")
@EntityListeners(AuditingEntityListener.class)
public class Note implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTES_SEQUENCE")
	@SequenceGenerator(name = "NOTES_SEQUENCE", sequenceName = "BLOG_NOTES_SEQ", allocationSize = 1)
	@Column(name = "NO_ID", precision = 19)
	private Long id;

	@Column(name = "NOTE_PARTIAL", precision = 19)
	private Integer notePartial;

	@Column(name = "CREATE_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "AT_ID")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Author author;
}
