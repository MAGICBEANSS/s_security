package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/test")
  public String testController() {
  return "<h1>Welcome1</h1>";
}



  @GetMapping("/home")
  public String homeController() {
    return "<h1>Home</h1>";
  }


  @GetMapping("/admin")
  public String adminController() {
    return "<h1>Admin</h1>";
  }

}
