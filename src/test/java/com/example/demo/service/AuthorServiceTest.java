package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Author;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.repository.AuthorRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthorServiceTest {

  @Mock
  private AuthorRepository authorRepositoryMock;
  @InjectMocks
  private AuthorService underTest;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllAuthorsHappyPath() {
    //GIVEN
    List<Author> expectedAuthors = List.of(
        Author.builder()
            .id(UUID.randomUUID())
            .name("name1")
            .dateOfBirth(LocalDate.now())
            .build(),
        Author.builder()
            .id(UUID.randomUUID())
            .name("name2")
            .dateOfBirth(LocalDate.now())
            .build()
    );

    when(authorRepositoryMock.findAll()).thenReturn(expectedAuthors);

    //WHEN
    List<Author> result = underTest.getAllAuthors();

    //THEN
    Assertions.assertIterableEquals(expectedAuthors, result);
  }

  @Test
  void findByIdWhenAuthorFound() {
    //GIVEN
    UUID id = UUID.randomUUID();
    Author expectedAuthor = Author.builder()
        .id(id)
        .name("name1")
        .dateOfBirth(LocalDate.now())
        .build();
    Optional<Author> expectedAuthorOptional = Optional.of(expectedAuthor);

    when(authorRepositoryMock.findById(id)).thenReturn(expectedAuthorOptional);

    //WHEN
    Author result = underTest.findById(id);

    //THEN
    assertEquals(expectedAuthor, result);
  }

  @Test
  void findByIdWhenAuthorIsMissing() {
    //GIVEN
    UUID id = UUID.randomUUID();

    String exceptionMessage = "There was no author with id: " + id;

    Optional<Author> expectedAuthorOptional = Optional.empty();

    when(authorRepositoryMock.findById(id)).thenReturn(expectedAuthorOptional);

    //WHEN
    Exception exception =
        assertThrows(NoSuchEntityException.class, () -> underTest.findById(id));

    //THEN
    assertEquals(exceptionMessage, exception.getMessage());

  }

  @Test
  void deleteByIDHappyPath() {
    //GIVEN
    UUID id = UUID.randomUUID();
    //WHEN
    underTest.deleteById(id);
    //THEN
    verify(authorRepositoryMock).deleteById(id);
  }

  @Test
  void saveHappyPath() {
    //GIVEN
    Author expectedAuthor = Author.builder()
        .id(UUID.randomUUID())
        .name("Kovács Ágnes")
        .dateOfBirth(LocalDate.now())
        .build();
    when(authorRepositoryMock.save(expectedAuthor)).thenReturn(expectedAuthor);
    //WHEN
    Author result = underTest.save(expectedAuthor);
    //THEN
    assertEquals(expectedAuthor, result);
  }

  @Test
  void editHappyPath() {
    //GIVEN
    Author expectedAuthor = Author.builder()
        .id(UUID.randomUUID())
        .name("Kovács Ágnes")
        .dateOfBirth(LocalDate.now())
        .build();
    when(authorRepositoryMock.save(expectedAuthor)).thenReturn(expectedAuthor);
    //WHEN
    Author result = underTest.save(expectedAuthor);
    //THEN
    assertEquals(expectedAuthor, result);
  }


}
