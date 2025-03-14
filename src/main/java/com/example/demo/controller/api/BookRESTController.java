package com.example.demo.controller.api;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
