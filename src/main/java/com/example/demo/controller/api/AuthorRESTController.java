package com.example.demo.controller.api;

import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/authors")
public class AuthorRESTController {

  @Autowired
  private AuthorService authorService;

  @GetMapping()
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

}
