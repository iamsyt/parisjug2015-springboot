package org.jsadaoui.demo.repository;

import org.jsadaoui.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring Data JPA repository for Book entities
 *
 * @author Julien Sadaoui
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public List<Book> findByAuthor(String author);
}
