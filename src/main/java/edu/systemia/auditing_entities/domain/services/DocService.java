package edu.systemia.auditing_entities.domain.services;

import edu.systemia.auditing_entities.infrastructure.dto.DocFileUploadDTO;
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
	 * @throws IOException if input stream has errors
	 * @see DocFileUploadDTO
	 */
	void uploadDocument(@NonNull DocFileUploadDTO uploadDTO) throws IOException;

}
