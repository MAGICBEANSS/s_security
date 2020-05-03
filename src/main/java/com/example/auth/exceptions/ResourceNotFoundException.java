package com.example.auth.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;


  public ResourceNotFoundException(String message) {
    super(message);
  }


}
