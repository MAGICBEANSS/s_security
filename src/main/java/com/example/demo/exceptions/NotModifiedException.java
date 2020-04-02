package com.example.demo.exceptions;

import lombok.Data;


@Data
public class NotModifiedException extends CustomParameterizedException {
  public NotModifiedException(String message, String... params) {
    super(message, params);
  }
}
