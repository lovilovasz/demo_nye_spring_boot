package com.example.demo.controller.api;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
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
@RequestMapping("api/books")
public class BookRESTController {

  @Autowired
  private BookService bookService;

  @GetMapping()
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable UUID id) {
    return bookService.findById(id);
  }

  @PostMapping("/create")
  public Book createBook(@RequestBody Book book) {
    return bookService.save(book);
  }

  @PostMapping("/update")
  public Book updateBook(@RequestBody Book book) {
    return bookService.edit(book);
  }

  @DeleteMapping("/{id}")
  public void deleteBookById(@PathVariable UUID id) {
    bookService.deleteById(id);
  }

}
