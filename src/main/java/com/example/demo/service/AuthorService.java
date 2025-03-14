package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  public List<Author> getAllAuthors() {
    return authors;
  }

  private List<Author> authors = new ArrayList<>(List.of(
      Author.builder()
          .id(UUID.randomUUID())
          .name("Name1")
          .dateOfBirth(LocalDate.of(1965,10,10))
          .build(),
      Author.builder()
          .id(UUID.randomUUID())
          .name("Name2")
          .dateOfBirth(LocalDate.of(1985,6,10))
          .build(),
      Author.builder()
          .id(UUID.randomUUID())
          .name("Name3")
          .dateOfBirth(LocalDate.of(1980,8,25))
          .build()
  ));

  public void save(Author author) {
    authors.add(author);
  }

  public void edit(Author author) {
    deleteById(author.getId());
    save(author);
  }

  public Author findById(UUID id) {
    return authors.stream().filter(author -> author.getId().equals(id)).toList().get(0);
  }

  public void deleteById(UUID id) {
    authors = new ArrayList<>(authors.stream().filter(author -> !author.getId().equals(id)).toList());
  }
}
