package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;
  @Autowired
  private AuthorService authorService;

  // GET: List all books (responds to /books/list)
  @GetMapping("/list")
  public String getAllBooks(Model model) {
    List<Book> books = bookService.getAllBooks();
    model.addAttribute("books", books);
    return "books/books"; // Updated template path (books directory)
  }

  // GET: Show Create Book Page
  @GetMapping("/new")
  public String createBookForm(Model model) {
    model.addAttribute("book", new Book());
    model.addAttribute("authors", authorService.getAllAuthors());
    return "books/create-book"; // Updated template path
  }

  // POST: Save New Book
  @PostMapping
  public String saveBook(@ModelAttribute Book book) {
    bookService.save(book);
    return "redirect:/books/list";
  }

  // GET: Show Edit Book Page
  @GetMapping("/edit/{id}")
  public String editBookForm(@PathVariable UUID id, Model model) {
    Book book = bookService.findById(id);
    model.addAttribute("book", book);
    model.addAttribute("authors", authorService.getAllAuthors());
    return "books/edit-book"; // Updated template path
  }

  // POST: Update Existing Book
  @PostMapping("/edit")
  public String updateBook(@ModelAttribute Book book) {
    bookService.edit(book);
    return "redirect:/books/list";
  }

  // POST: Delete Book
  @PostMapping("/delete/{id}")
  public String deleteBook(@PathVariable UUID id) {
    bookService.deleteById(id);
    return "redirect:/books/list";
  }
}
