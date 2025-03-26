package com.example.demo.controller.api;

import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/authors") // http://localhost:8080/api/authors
public class AuthorRESTController {

  @Autowired
  private AuthorService authorService;

  @GetMapping()
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @GetMapping("/{id}")
  public Author getAuthorById(@PathVariable UUID id) {
    return authorService.findById(id);
  }

  @PostMapping("/create")
  public Author createAuthor(@RequestBody Author author) {
    return authorService.save(author);
  }

  @PostMapping("/update")
  public Author updateAuthor(@RequestBody Author author) {
    return authorService.edit(author);
  }

  @DeleteMapping("/{id}")
  public void deleteAuthor(@PathVariable UUID id) {
    authorService.deleteById(id);
  }

}
