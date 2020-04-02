package com.example.demo.exceptions;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
