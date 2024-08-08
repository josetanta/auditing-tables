package edu.systemia.auditing_entities.domain.services;

import edu.systemia.auditing_entities.infrastructure.dto.AuthorQueryResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

	/**
	 * Pagination of All Authors
	 *
	 * @param pageable        pagination
	 * @param filterFirstname filter by firstname
	 * @return Page of Author
	 */
	Page<AuthorQueryResult> paginateAuthor(Pageable pageable, String filterFirstname);

}
