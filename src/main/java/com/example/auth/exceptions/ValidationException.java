package com.example.auth.exceptions;


import com.example.auth.config.constants.ExceptionCode;
import lombok.Data;


@Data
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;


  private ExceptionCode exceptionCode;

  public ValidationException(String message) {
    super(message);
    setExceptionCode(ExceptionCode.DEFAULT);

  }

  public ValidationException(String message, Throwable t) {
    super(message, t);
    setExceptionCode(ExceptionCode.DEFAULT);
  }



  public ValidationException(String message, ExceptionCode exceptionCode) {
    super(message);
    setExceptionCode(exceptionCode);
  }
}
