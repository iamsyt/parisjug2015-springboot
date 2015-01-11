package org.jsadaoui.demo.web;

import org.jsadaoui.demo.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {
    @Inject
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldCreateBookSpringParLaPratique() throws Exception {
        //given
        String content = "{\"isbn\":\"9782212124217\",\"title\":\"Spring par la Pratique Spring 2.5 et 3.0\",\"author\":\"Julien Dubois\",\"price\":\"45.60\"}";

        // when
        mvc.perform(post("/rest/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content.getBytes()))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        // when
        mvc.perform(get("/rest/books")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetBooksByJoshuaBloch() throws Exception {
        // when
        mvc.perform(get("/rest/books/author/Joshua Bloch")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetBookJavaEffective() throws Exception {
        // when
        mvc.perform(get("/rest/books/9780321356680")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("9780321356680"))
                .andExpect(jsonPath("$.title").value("Java Effective"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    public void shouldDeleteBookJavaEffective() throws Exception {
        // when
        mvc.perform(delete("/rest/books/9780321356680")
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk());
    }
}