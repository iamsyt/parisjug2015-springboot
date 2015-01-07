package org.jsadaoui.demo.web;

import org.jsadaoui.demo.Application;
import org.jsadaoui.demo.domain.Book;
import org.jsadaoui.demo.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class BookControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Inject
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private Book bookSpringParLaPratique;

    @Inject
    private Book bookSpringInAction;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac).build();
        bookRepository.save(bookSpringInAction);
    }

    @Test
    public void shouldCreateBookSpringParLaPratique() throws Exception {
        // when
        ResponseEntity response = new TestRestTemplate()
                .postForEntity("http://localhost:" + port + "/rest/books", bookSpringParLaPratique, Book.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        // when
        mvc.perform(get("/rest/books")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldGetBookSpringInAction() throws Exception {
        // when
        mvc.perform(get("/rest/books/9781935182351")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("9781935182351"))
                .andExpect(jsonPath("$.title").value("Spring in Action"))
                .andExpect(jsonPath("$.author").value("Craig Walls"));
    }

    @Test
    public void shouldDeleteBookSpringInAction() throws Exception {
        // when
        mvc.perform(delete("/rest/books/9781935182351")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk());
    }

    @Configuration
    static class BookControllerTestConfiguration {

        @Bean
        public Book bookSpringParLaPratique() {
            Book book = new Book();
            book.setIsbn("9782212124217");
            book.setTitle("Spring par la Pratique Spring 2.5 et 3.0");
            book.setAuthor("Julien Dubois");
            book.setPrice(new BigDecimal("45.60"));
            return book;
        }

        @Bean
        public Book bookSpringInAction() {
            Book book = new Book();
            book.setIsbn("9781935182351");
            book.setTitle("Spring in Action");
            book.setAuthor("Craig Walls");
            book.setPrice(new BigDecimal("42.40"));
            return book;
        }
    }
}
