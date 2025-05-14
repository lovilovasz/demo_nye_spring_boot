package com.example.demo.controller.api;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorRESTControllerTest {

  @Mock
  private AuthorService authorService;

  @InjectMocks
  private AuthorRESTController authorRESTController;

  ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mockMvc;

  private Author author;

  private UUID id;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(authorRESTController).build();
    id = UUID.randomUUID();
    author = Author.builder().id(id).name("Kristof").build();
  }

  @Test
  void testGetAllAuthors() throws Exception {
    when(authorService.getAllAuthors()).thenReturn(List.of(author));

    mockMvc.perform(get("/api/authors"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(id.toString()))
        .andExpect(jsonPath("$[0].name").value("Kristof"));
  }

  @Test
  void testGetAuthorById() throws Exception {
    when(authorService.findById(id)).thenReturn(author);

    mockMvc.perform(get("/api/authors/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id.toString()))
        .andExpect(jsonPath("$.name").value("Kristof"));
  }

  @Test
  void testCreateAuthor() throws Exception {
    when(authorService.save(any(Author.class))).thenReturn(author);

    mockMvc.perform(post("/api/authors/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(author)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id.toString()))
        .andExpect(jsonPath("$.name").value("Kristof"));
  }

  @Test
  void testUpdateAuthor() throws Exception {
    when(authorService.edit(author)).thenReturn(author);

    mockMvc.perform(post("/api/authors/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(author))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id.toString()))
        .andExpect(jsonPath("$.name").value("Kristof"));
  }

  @Test
  void testDeleteAuthor() throws Exception {
    doNothing().when(authorService).deleteById(id);

    mockMvc.perform(delete("/api/authors/{id}", id))
        .andExpect(status().isOk());

    verify(authorService).deleteById(id);
  }

}
