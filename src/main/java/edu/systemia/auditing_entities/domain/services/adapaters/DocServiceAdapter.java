package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.DocService;
<<<<<<< HEAD
import edu.systemia.auditing_entities.domain.dto.DocFileUploadDTO;
=======
import edu.systemia.auditing_entities.infrastructure.dto.DocFileUploadDTO;
<<<<<<< HEAD
>>>>>>> f9268a3 (feat: introspector HIBERNATE)
=======
>>>>>>> 6db1167 (Dev)
>>>>>>> 8fbd36e (resolve)
import edu.systemia.auditing_entities.infrastructure.persistence.entity.BlogDoc;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Course;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.BlogDocRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class DocServiceAdapter implements DocService {

	private final BlogDocRepository repository;

	@Override
	public void uploadDocument(@NonNull DocFileUploadDTO uploadDTO) throws IOException {

		String filenameExtension = StringUtils.getFilenameExtension(uploadDTO.filename());

		log.info("upload filename= filenameExtension={}", filenameExtension);

		var newBlogDoc = BlogDoc.builder()
			.blogCourse(Course.builder().id(uploadDTO.courseId()).build())
			.dcData(uploadDTO.file().getBytes())
			.dcFilename(uploadDTO.filename())
			.build();

		BlogDoc save = repository.save(newBlogDoc);
		log.info("saved file ID={}", save.getDcId());
	}
}
