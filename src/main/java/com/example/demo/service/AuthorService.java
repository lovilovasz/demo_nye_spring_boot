package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  public void save(Author author) {
    authorRepository.save(author);
  }

  public void edit(Author author) {
    authorRepository.save(author);
  }

  public Author findById(UUID id) {
    Optional<Author> optionalAuthor = authorRepository.findById(id);
    if(optionalAuthor.isPresent()) {
      return optionalAuthor.get();
    } else {
      throw new NoSuchEntityException("There was no author with id: " + id);
    }
  }

  public void deleteById(UUID id) {
    authorRepository.deleteById(id);
  }
}
