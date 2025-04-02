package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepositoryMock;

  @InjectMocks
  private BookService underTest;

  @Test
  void getAllBooks() {
    //GIVEN
    List<Book> expectedBooks = List.of(
        Book.builder()
            .id(UUID.randomUUID())
            .title("Elso konyv")
            .publicationDate(LocalDate.now())
            .build(),
        Book.builder()
            .id(UUID.randomUUID())
            .title("Elso konyv")
            .publicationDate(LocalDate.now())
            .build()
    );
    when(bookRepositoryMock.findAll()).thenReturn(expectedBooks);
    //WHEN
    List<Book> result = underTest.getAllBooks();
    //THEN
    assertEquals(expectedBooks, result);
  }

  @Test
  void saveHappyPath() {
    //GIVEN
    Book expectedBook = Book.builder()
        .id(UUID.randomUUID())
        .publicationDate(LocalDate.now())
        .title("Elso konyv")
        .build();
    when(bookRepositoryMock.save(expectedBook)).thenReturn(expectedBook);
    //WHEN
    Book result = underTest.save(expectedBook);
    //THEN
    assertEquals(expectedBook, result);
  }

  @Test
  void editHappyPath() {
    //GIVEN
    Book expectedBook = Book.builder()
        .id(UUID.randomUUID())
        .publicationDate(LocalDate.now())
        .title("Elso konyv")
        .build();
    when(bookRepositoryMock.save(expectedBook)).thenReturn(expectedBook);
    //WHEN
    Book result = underTest.save(expectedBook);
    //THEN
    assertEquals(expectedBook, result);
  }


}
