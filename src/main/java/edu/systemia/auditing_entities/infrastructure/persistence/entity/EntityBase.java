package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import edu.systemia.auditing_entities.infrastructure.persistence.converters.StringYNToBooleanConverter;
import edu.systemia.auditing_entities.infrastructure.persistence.listeners.StatusListener;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(StatusListener.class)
public class EntityBase implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT", insertable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(name = "ACTIVATE_AT")
	private LocalDateTime activateAt;

	@Column(name = "DEACTIVATE_AT")
	private LocalDateTime deactivateAt;

	@Column(name = "E_ACTIVE", length = 1, nullable = false)
	@Convert(converter = StringYNToBooleanConverter.class)
	private Boolean active;
}
