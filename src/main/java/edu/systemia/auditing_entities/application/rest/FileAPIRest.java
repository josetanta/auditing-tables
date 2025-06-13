package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.domain.services.DocService;
import edu.systemia.auditing_entities.infrastructure.dto.DocFileUploadDTO;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Validated
public class FileAPIRest {

	private final DocService docService;

	@PostMapping("/upload/{course-id}")
	public ResponseEntity<String> postUploadFile(
		@PathVariable("course-id")
		@Min(1)
		int courseId,

		@RequestParam("file")
		MultipartFile file
	) throws IOException {

		var uploadFile = new DocFileUploadDTO(
			file,
			file.getOriginalFilename(),
			courseId // reference ID
		);
		docService.uploadDocument(uploadFile);
		return ResponseEntity.ok("OK");
	}

}
