package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BLOG_SUBSCRIPTION")
@EntityListeners(AuditingEntityListener.class)
public class Subscription implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SUBSCRIPTION_SEQUENCE")
	@SequenceGenerator(name = "SUBSCRIPTION_SEQUENCE", sequenceName = "BLOG_SUBSCRIPTION_SEQ", allocationSize = 1)
	@Column(name = "SUB_ID", precision = 19)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "AT_ID")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Author author;

	@ManyToOne
	@JoinColumn(name = "CO_ID")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Course course;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "CREATE_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
}
