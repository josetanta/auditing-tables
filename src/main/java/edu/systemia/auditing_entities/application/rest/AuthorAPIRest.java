package edu.systemia.auditing_entities.application.rest;

import edu.systemia.auditing_entities.domain.services.AuthorService;
import edu.systemia.auditing_entities.infrastructure.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.mappers.AuthorMapper;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorAPIRest {
	private final AuthorRepository authorRepository;
	private final AuthorService authorService;
	private final AuthorMapper mapper;

	@PostMapping
	public ResponseEntity<AuthorDTO> postCreateAuthor(@RequestBody AuthorDTO dto) {
		final var cycleAvoid = new CycleAvoidingMappingContext();
		var author = mapper.mapToModel(dto, cycleAvoid);
		var authorSaved = authorRepository.save(author);
		var authorDTO = mapper.mapToDto(authorSaved, cycleAvoid);
		return ResponseEntity.created(URI.create("")).body(authorDTO);
	}

	@PutMapping
	public ResponseEntity<AuthorDTO> putUpdateAuthor(@RequestBody AuthorDTO dto) {

		if (Objects.isNull(dto.id())) {
			return ResponseEntity.badRequest().build();
		}

		if (!authorRepository.existsById(dto.id())) {
			return ResponseEntity.notFound().build();
		}

		final var cycleAvoid = new CycleAvoidingMappingContext();
		var author = mapper.mapToModel(dto, cycleAvoid);
		var authorSaved = authorRepository.save(author);
		var authorDTO = mapper.mapToDto(authorSaved, cycleAvoid);
		return ResponseEntity.ok(authorDTO);
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

	@GetMapping(path = "/author-dto-view")
	public ResponseEntity<Object> getAuthorDTOViewAll(
		@PageableDefault(size = 5) Pageable pageable,
		@RequestParam String firstname
	) {
		var authors = authorService.paginateAuthor(pageable, firstname);
		return ResponseEntity.ok(authors);
	}
}
