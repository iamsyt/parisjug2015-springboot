package org.jsadaoui.demo.repository;

import org.jsadaoui.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a collection of books
 *
 * @author Julien Sadaoui
 */
@Repository
public class BookRepository {
    private final Map<String,Book> books = new HashMap<>();

    /**
     *  Gets a book with the given isbn
     *
     * @param isbn of book
     * @return book to search
     */
    public Book get(String isbn) {
        return books.get(isbn);
    }

    /**
     *  Gets all books
     *
     * @return
     */
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    /**
     * Add the given book or replace the old book
     *
     * @param book to added or updated
     */
    public Book add(Book book) {
        return books.put(book.getIsbn(), book);
    }

    /**
     *  Removes the given book
     *
     * @param book to deleted
     */
    public Book delete(Book book) {
        return books.remove(book.getIsbn());
    }
}
