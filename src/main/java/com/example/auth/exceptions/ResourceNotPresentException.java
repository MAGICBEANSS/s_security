package com.example.auth.exceptions;

import lombok.Data;

@Data
public class ResourceNotPresentException extends Exception {

	private static final long serialVersionUID = 1L;


	public ResourceNotPresentException(String message) {
		super(message);
	}

}
