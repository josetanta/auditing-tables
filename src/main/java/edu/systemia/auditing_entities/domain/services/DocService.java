package edu.systemia.auditing_entities.domain.services;

<<<<<<< HEAD
import edu.systemia.auditing_entities.domain.dto.DocFileUploadDTO;
=======
import edu.systemia.auditing_entities.infrastructure.dto.DocFileUploadDTO;
>>>>>>> f9268a3 (feat: introspector HIBERNATE)
import org.springframework.lang.NonNull;

import java.io.IOException;

public interface DocService {

	/**
	 * Uploads a document to the system with the provided metadata and content.
	 *
	 * <p>The implementation should:
	 * <ul>
	 *   <li>Validate the input DTO</li>
	 *   <li>Process the document content</li>
	 *   <li>Store the document in persistent storage</li>
	 *   <li>Record document metadata</li>
	 * </ul>
	 *
	 * @param uploadDTO the document upload data transfer object containing:
	 *                  <ul>
	 *                    <li>Document metadata (name, type, etc.)</li>
	 *                    <li>Document content (bytes or stream)</li>
	 *                    <li>Optional security/access parameters</li>
	 *                  </ul>
	 * @throws IllegalArgumentException if uploadDTO is null or contains invalid data
<<<<<<< HEAD
	 * @throws IOException              if input stream has errors
=======
	 * @throws IOException if input stream has errors
>>>>>>> f9268a3 (feat: introspector HIBERNATE)
	 * @see DocFileUploadDTO
	 */
	void uploadDocument(@NonNull DocFileUploadDTO uploadDTO) throws IOException;

}
