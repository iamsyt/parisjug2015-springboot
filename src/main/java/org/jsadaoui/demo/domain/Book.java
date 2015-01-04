package org.jsadaoui.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  This class represents a book
 *
 * @author Julien Sadaoui
 */
@Data
public class Book {
    private String isbn;
    private String name;
    private String author;
    private BigDecimal price;
}
