<<<<<<< HEAD:src/main/java/edu/systemia/auditing_entities/domain/dto/DocFileUploadDTO.java
package edu.systemia.auditing_entities.domain.dto;
=======
package edu.systemia.auditing_entities.infrastructure.dto;
>>>>>>> f9268a3 (feat: introspector HIBERNATE):src/main/java/edu/systemia/auditing_entities/infrastructure/dto/DocFileUploadDTO.java

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record DocFileUploadDTO(
	@NotNull
	MultipartFile file,

	String filename,

	@Min(value = 1)
	long courseId
) implements Serializable {

}
