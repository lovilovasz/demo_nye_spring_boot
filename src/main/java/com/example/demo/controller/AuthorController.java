package com.example.demo.controller;

import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  // GET: List all authors (responds to /authors/list)
  @GetMapping("/list")
  public String getAllAuthors(Model model) {
    List<Author> authors = authorService.getAllAuthors();
    model.addAttribute("authors", authors);
    return "authors/authors"; // Template location in authors directory
  }

  // GET: Show Create Author Page
  @GetMapping("/new")
  public String createAuthorForm(Model model) {
    model.addAttribute("author", new Author());
    return "authors/create-author"; // Template for creating authors
  }

  // POST: Save New Author
  @PostMapping
  public String saveAuthor(@ModelAttribute Author author) {
    authorService.save(author);
    return "redirect:/authors/list"; // Redirect to /authors/list after saving
  }

  // GET: Show Edit Author Page
  @GetMapping("/edit/{id}")
  public String editAuthorForm(@PathVariable UUID id, Model model) {
    Author author = authorService.findById(id);
    model.addAttribute("author", author);
    return "authors/edit-author"; // Template for editing authors
  }

  // POST: Update Existing Author
  @PostMapping("/edit")
  public String updateAuthor(@ModelAttribute Author author) {
    authorService.edit(author);
    return "redirect:/authors/list"; // Redirect to /authors/list after updating
  }

  // POST: Delete Author
  @PostMapping("/delete/{id}")
  public String deleteAuthor(@PathVariable UUID id) {
    authorService.deleteById(id);
    return "redirect:/authors/list"; // Redirect to /authors/list after deleting
  }
}