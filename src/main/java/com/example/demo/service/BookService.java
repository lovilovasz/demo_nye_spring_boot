package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  public List<Book> getAllBooks() {
    return books;
  }

  private List<Book> books = new ArrayList<>(List.of(
      Book.builder()
          .id(UUID.randomUUID())
          .title("title1")
          .publicationDate(LocalDate.now())
          .author(
              Author.builder()
                  .id(UUID.randomUUID())
                  .name("Name1")
                  .dateOfBirth(LocalDate.of(1965,10,10))
                  .build()
          )
          .build(),
      Book.builder()
          .id(UUID.randomUUID())
          .title("title2")
          .publicationDate(LocalDate.now())
          .author(
              Author.builder()
                  .id(UUID.randomUUID())
                  .name("Name2")
                  .dateOfBirth(LocalDate.of(1985,6,10))
                  .build()
          )
          .build(),
      Book.builder()
          .id(UUID.randomUUID())
          .title("title3")
          .publicationDate(LocalDate.now())
          .author(
              Author.builder()
                  .id(UUID.randomUUID())
                  .name("Name3")
                  .dateOfBirth(LocalDate.of(1980,8,25))
                  .build()
          )
          .build()
  ));

  public void save(Book book) {
    books.add(book);
  }

  public void edit(Book book) {
    deleteById(book.getId());
    save(book);
  }

  public Book findById(UUID id) {
    return books.stream().filter(book -> book.getId().equals(id)).toList().get(0);
  }

  public void deleteById(UUID id) {
    books = new ArrayList<>(books.stream().filter(book -> !book.getId().equals(id)).toList());
  }
}
