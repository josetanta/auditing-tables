package edu.systemia.auditing_entities.domain.services;

import edu.systemia.auditing_entities.infrastructure.dto.AuthorQueryResult;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itextpdf.text.DocumentException;

public interface AuthorService {

	/**
	 * Pagination of All Authors
	 *
	 * @param pageable        pagination
	 * @param filterFirstname filter by firstname
	 * @return Page of Author
	 */
	Page<AuthorQueryResult> paginateAuthor(Pageable pageable, String filterFirstname);

	/**
	 * Exported to PDF all Authors
	 * @return bytes
	 */
	ByteArrayResource exportPdf() throws IOException, DocumentException;
}
