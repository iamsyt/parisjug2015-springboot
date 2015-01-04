package org.jsadaoui.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jsadaoui.demo.Application;
import org.jsadaoui.demo.domain.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class BookControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Value("${local.server.port}")
    private int port;


    RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreateBook() throws Exception {
        // given
        Book book = new Book();
        book.setIsbn("221212421X");
        book.setName("Spring par la Pratique Spring 2.5 et 3.0");
        book.setAuthor("Julien Dubois");
        book.setPrice(new BigDecimal("45.60"));

        // when
        ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:" + port + "/rest/book/create", book, Book.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        Book result = response.getBody();
        assertThat(result, notNullValue());
        assertThat(result.getIsbn(), equalTo("221212421X"));
        assertThat(result.getAuthor(), equalTo("Julien Dubois"));

    }
}
