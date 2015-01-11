package org.jsadaoui.demo.web;

import org.jsadaoui.demo.domain.Book;
import org.jsadaoui.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST Services for {@link org.jsadaoui.demo.domain.Book} entity
 *
 * @author Julien Sadaoui
 */
@RestController
@RequestMapping(value = "/rest/books")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list() {
        LOG.debug("REST request to get all Books");
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        LOG.debug("REST request to get book : {}", isbn);
        return Optional.ofNullable(bookRepository.findOne(isbn))
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
    public List<Book> listByAuthor(@PathVariable("author") String author) {
        LOG.debug("REST request to get alls books : {}", author);
        return bookRepository.findByAuthor(author);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@Valid @RequestBody Book book) {
        LOG.debug("REST request to save book : {}", book);
        bookRepository.save(book);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("isbn") String isbn) {
        LOG.debug("REST request to delete book : {}", isbn);
        bookRepository.delete(isbn);
    }
}
