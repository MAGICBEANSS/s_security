package com.example.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/test")
  public String testController() {
  return "<h1>Welcome1</h1>";
}



  @GetMapping(value = {"/home/{user}"})
  public String homeController(@PathVariable("user") String user) {
    return "<h1>Hello </h1>"+user;
  }

@GetMapping(value = {"/user","/home"})
  public String homeController() {
    return "<h1>Home</h1>";
  }


  @GetMapping("/admin")
  public String adminController() {
    return "<h1>Admin</h1>";
  }


  @GetMapping("/private")
  public String privateController() {
    return "<h1>Admin</h1>";
  }


  @GetMapping("/management/{mid}")
  public String privateController(@PathVariable("mid") String mid) {
    return "<h1>Admin</h1>"+mid;
  }

}
