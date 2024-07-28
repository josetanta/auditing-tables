package edu.systemia.auditing_entities.application.rest;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorAPIRest {

    private final AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<Author> postCreateAuthor(@RequestBody Author author) {

	var authorSaved = authorRepository.save(author);
	return ResponseEntity.created(URI.create("")).body(authorSaved);
    }

    @GetMapping
    public ResponseEntity<Page<Author>> getFindAllAuthor(Pageable pageable) {
	log.info("Pagination by {}", pageable);

	Page<Author> result = authorRepository.findAll(pageable);
	log.info("Result its size is {}", result.getSize());

	return ResponseEntity.ok(result);
    }

    @GetMapping("/{author-id}")
    public ResponseEntity<Author> getFindAuthorByID(@PathVariable("author-id") Long id) {
	log.info("Find by {}", id);

	Author author = authorRepository.findById(id).orElseThrow();
	log.info("Results is {}", author);

	return ResponseEntity.ok(author);
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
