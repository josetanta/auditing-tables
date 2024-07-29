package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.infrastructure.persistence.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.mappers.AuthorMapper;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorAPIRest {
	private final AuthorRepository authorRepository;
	private final AuthorMapper mapper;

	@PostMapping
	public ResponseEntity<AuthorDTO> postCreateAuthor(@RequestBody AuthorDTO dto) {
		final var cycleAvoid = new CycleAvoidingMappingContext();
		var author = mapper.mapToModel(dto, cycleAvoid);
		var authorSaved = authorRepository.save(author);
		var authorDTO = mapper.mapToDto(authorSaved, cycleAvoid);
		return ResponseEntity.created(URI.create("")).body(authorDTO);
	}

	@GetMapping
	public ResponseEntity<Page<Author>> getFindAllAuthor(Pageable pageable) {
		log.info("Pagination by {}", pageable);

		Page<Author> result = authorRepository.findAll(pageable);
		log.info("Result its size is {}", result.getSize());

		return ResponseEntity.ok(result);
	}

	@GetMapping("/{author-id}")
	public ResponseEntity<Object> getFindAuthorByID(@PathVariable("author-id") Long id) {
		log.info("Find by {}", id);

		var author = authorRepository.findById(id).orElseThrow();
		var authorDTO = mapper.mapToDto(author, new CycleAvoidingMappingContext());
		log.info("Results is {}", author);

		return ResponseEntity.ok(authorDTO);
	}

	@GetMapping("proc-next-value")
	public ResponseEntity<String> getNextValue() {
		return ResponseEntity.ok(authorRepository.getProcedureSeqValue());
	}

	@GetMapping(path = "/author-view")
	public ResponseEntity<Object> getAuthorViewAll(Pageable pageable, @RequestParam String firstname) {
		var authors = authorRepository.findAllByFirstnameContaining(pageable, firstname);
		return ResponseEntity.ok(authors);
	}
}
