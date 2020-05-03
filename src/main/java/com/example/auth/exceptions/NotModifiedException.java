package com.example.auth.exceptions;

import lombok.Data;


@Data
public class NotModifiedException extends CustomParameterizedException {
  public NotModifiedException(String message, String... params) {
    super(message, params);
  }
}
