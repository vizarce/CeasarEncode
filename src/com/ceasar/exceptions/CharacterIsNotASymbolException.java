package com.ceasar.exceptions;

public class CharacterIsNotASymbolException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
    public CharacterIsNotASymbolException() {
        super();
    }
    public CharacterIsNotASymbolException(final String message) {
        super(message);
    }
    public CharacterIsNotASymbolException(final String message, final Throwable cause) {
        super(message, cause);
    }
}