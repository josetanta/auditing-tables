package edu.systemia.auditing_entities.infrastructure.dto;

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
