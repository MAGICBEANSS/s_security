package com.example.demo.exceptions;

import lombok.Data;

@Data
public class RemoteServiceException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public RemoteServiceException(String message) {
    super(message);
  }
}
