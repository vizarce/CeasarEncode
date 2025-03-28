package com.ceasar.exceptions;

public class NotAuthenticatedException extends RuntimeException {
     
	private static final long serialVersionUID = 1L;
	
    public NotAuthenticatedException() {
        super();
    }
    public NotAuthenticatedException(final String message) {
        super(message);
    }
    public NotAuthenticatedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
