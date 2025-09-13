package edu.systemia.auditing_entities.application.rest;

import com.itextpdf.text.DocumentException;
import edu.systemia.auditing_entities.application.exceptions.ResourceNotFoundException;
import edu.systemia.auditing_entities.domain.services.AuthorService;
import edu.systemia.auditing_entities.domain.dto.AuthorDTO;
import edu.systemia.auditing_entities.domain.dto.NoteDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Note;
import edu.systemia.auditing_entities.infrastructure.persistence.mappers.AuthorMapper;
import edu.systemia.auditing_entities.infrastructure.persistence.mappers.NoteMapper;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.NoteRepository;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Validated
public class AuthorAPIRest {
	private final AuthorRepository authorRepository;
	private final NoteRepository noteRepository;
	private final AuthorService authorService;
	private final AuthorMapper mapper;
	private final NoteMapper noteMapper;
	private final CycleAvoidingMappingContext cycleAvoid;

	@PostMapping
	public ResponseEntity<AuthorDTO> postCreateAuthor(@RequestBody AuthorDTO dto) {
		var author = mapper.toModel(dto, new CycleAvoidingMappingContext());
		var authorDTO = mapper.toDto(authorRepository.save(author), cycleAvoid);
		log.info("Created an user successfully");
		return ResponseEntity.created(URI.create("")).body(authorDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<AuthorDTO> putUpdateAuthor(@RequestBody AuthorDTO dto) {
		return performUpdateAuthor(dto, false);
	}

	@PutMapping("/update/force")
	public ResponseEntity<AuthorDTO> putUpdateAuthorForce(@RequestBody AuthorDTO dto) {
		return performUpdateAuthor(dto, true);
	}

	private ResponseEntity<AuthorDTO> performUpdateAuthor(final AuthorDTO dto, boolean isForce) {
		if (Objects.isNull(dto.getId())) {
			return ResponseEntity.badRequest().build();
		}

		if (!authorRepository.existsById(dto.getId())) {
			return ResponseEntity.notFound().build();
		}

		var author = mapper.toModel(dto, cycleAvoid);

		if (isForce) {
			authorRepository.updateFirstname(author.getId(), author.getFirstname());
		}

		var authorDTO = mapper.toDto(authorRepository.save(author), cycleAvoid);
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
		var authorDTO = mapper.toDto(author, cycleAvoid);
		log.info("Results is {}", author);

		return ResponseEntity.ok(authorDTO);
	}

	@GetMapping("proc-next-value")
	public ResponseEntity<String> getNextValue() {
		return ResponseEntity.ok(authorRepository.getProcedureSeqValue());
	}

	@GetMapping(path = "/author-view")
	public ResponseEntity<Object> getAuthorViewAll(
		Pageable pageable,
		@RequestParam(defaultValue = "") String firstname
	) {
		var authors = authorRepository.findAllByFirstnameContaining(pageable, firstname);
		return ResponseEntity.ok(authors);
	}


	@GetMapping(path = "/author-view-dto")
	public ResponseEntity<Object> getAuthorViewDTO(
		Pageable pageable
	) {
		var authors = authorRepository.findAll(pageable);
		Page<AuthorDTO> result = mapper.toDto(authors, cycleAvoid);
		log.info("Pagination of authors");
		return ResponseEntity.ok(result);
	}

	@GetMapping(path = "/author-dto-view")
	public ResponseEntity<Object> getAuthorDTOViewAll(@PageableDefault(size = 5) Pageable pageable,
													  @RequestParam String firstname) {
		var authors = authorService.paginateAuthor(pageable, firstname);
		return ResponseEntity.ok(authors);
	}

	@GetMapping(path = "/export-pdf", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<Object> getExportPDF() {
		try {
			ByteArrayResource exportPdf = authorService.exportPdf();
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(exportPdf);

		} catch (IOException | DocumentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to export PDF");
		}
	}

	@PostMapping(path = "/create-notes/{author-id}")
	public ResponseEntity<Object> postCreateNoteByAuthor(
		@PathVariable("author-id") long authorId,
		@RequestBody NoteDTO noteDTO
	) {
		Note noteEntity = noteMapper.toModel(noteDTO, cycleAvoid);
		Author author = new Author();
		author.setId(authorId);
		noteEntity.setAuthor(author);
		Note result = noteRepository.save(noteEntity);
		log.info("Note created {}", result.getId());
		return ResponseEntity.ok(result.getId());
	}

	@GetMapping(path = "/notes/{author-id}")
	public ResponseEntity<Object> getPageNoteByAuthor(
		@PathVariable("author-id") long authorId,
		Pageable pageable
	) {
		Page<Note> result = noteRepository.getAllByAuthor_Id(pageable, authorId);
		Page<NoteDTO> resultDTO = result.map(note -> noteMapper.toDto(note, cycleAvoid));
		log.info("Pagination of NotesDTO");
		return ResponseEntity.ok(resultDTO);
	}

	@DeleteMapping("/{author-id}")
	public ResponseEntity<Object> deleteRemoveAuthorByID(
		@PathVariable("author-id") Long authorId
	) {

		if (!authorRepository.existsById(authorId)) {
			throw new ResourceNotFoundException(
				"Resource not exist",
				"es",
				"ERROR.NOT_FOUND"
			);
		}

		authorRepository.deleteById(authorId);
		log.info("Author with id {} DELETED", authorId);

		return ResponseEntity.noContent()
			.build();
	}

}
