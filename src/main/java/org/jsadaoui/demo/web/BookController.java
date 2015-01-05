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

/**
 * REST Services for {@link org.jsadaoui.demo.domain.Book} entity
 *
 * @author Julien Sadaoui
 */
@RestController
@RequestMapping(value = "/rest/books")
public class BookController {

    @Inject
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list() {
        return bookRepository.getAll();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        Book book = bookRepository.get(isbn);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        bookRepository.add(book);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public Book delete(@PathVariable("isbn") String isbn) {
        Book book = bookRepository.get(isbn);
        return bookRepository.delete(book);
    }
}
