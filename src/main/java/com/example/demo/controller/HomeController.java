package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/") // Maps to the root URL
  public String index() {
    return "index"; // Redirects to the `index.html` Thymeleaf template
  }
}
