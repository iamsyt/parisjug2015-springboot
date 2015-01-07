package org.jsadaoui.demo.domain;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * This class represents a book
 *
 * @author Julien Sadaoui
 */
@Data
public class Book {
    @NotNull
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    private String isbn;

    @NotNull
    @Size(min = 5)
    private String title;

    @NotNull
    @Size(min = 10)
    private String author;

    @DecimalMin("0.00")
    private BigDecimal price;
}
