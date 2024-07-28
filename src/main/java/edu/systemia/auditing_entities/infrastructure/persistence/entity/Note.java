package edu.systemia.auditing_entities.infrastructure.persistence.entity;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BLOG_NOTES")
@EntityListeners(AuditingEntityListener.class)
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTES_SEQUENCE")
	@SequenceGenerator(name = "NOTES_SEQUENCE", sequenceName = "BLOG_BLOG_NOTES_SEQ", allocationSize = 1)
	@Column(name = "NO_ID", precision = 19)
	private Long id;

	@Column(name = "NOTE_PARTIAL", precision = 19)
	private Integer notePartial;

	@Column(name = "CREATE_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "AT_ID")
	@ToString.Exclude
	@JsonBackReference
	private Author author;
}
