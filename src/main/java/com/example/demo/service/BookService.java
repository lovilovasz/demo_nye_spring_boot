package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public void save(Book book) {
    bookRepository.save(book);
  }

  public void edit(Book book) {
    bookRepository.save(book);
  }

  public Book findById(UUID id) {
    Optional<Book> optionalBook = bookRepository.findById(id);
    if (optionalBook.isPresent()) {
      return optionalBook.get();
    } else {
      throw new NoSuchEntityException("There was no book found for this id: " + id);
    }
  }

  public void deleteById(UUID id) {
    bookRepository.deleteById(id);
  }
}
