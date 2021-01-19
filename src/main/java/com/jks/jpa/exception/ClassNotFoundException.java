package com.jks.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public ClassNotFoundException() {
	        super();
	    }

	    public ClassNotFoundException(String message) {
	        super(message);
	    }

	    public ClassNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	}