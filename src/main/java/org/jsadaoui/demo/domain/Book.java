package org.jsadaoui.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents a book
 *
 * @author Julien Sadaoui
 */
@Data
@Entity
public class Book implements Serializable {
    @NotNull
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    @Id
    private String isbn;

    @NotNull
    @Size(min = 5)
    @Column(nullable = false)
    private String title;

    @NotNull
    @Size(min = 10)
    @Column(nullable = false)
    private String author;

    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal price;
}
