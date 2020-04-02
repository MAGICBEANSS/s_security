package com.example.demo.exceptions;


import lombok.Data;

@Data
public class ValidationFailedException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidationFailedException(String message) {
		super(message);
	}
}
