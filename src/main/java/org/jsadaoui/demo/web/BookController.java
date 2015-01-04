package org.jsadaoui.demo.web;

import org.jsadaoui.demo.domain.Book;
import org.jsadaoui.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * REST Services for {@link org.jsadaoui.demo.domain.Book} entity
 *
 * @author Julien Sadaoui
 */
@RestController
@RequestMapping("/rest/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        Book book = bookRepository.get(isbn);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book) {
        return bookRepository.add(book);
    }

    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.POST)
    public Book deleteBook(@PathVariable("isbn") String isbn) {
        Book book = bookRepository.get(isbn);
        return bookRepository.delete(book);
    }
}
