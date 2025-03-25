package com.ceasar.encryption.interfaces;

public enum Mode {
    ENCODE("encode"),
    DECODE("decode");
	
    private Mode(final String mode) {
    	this.mode = mode;	
    }   
    private String mode;
	
	public String getMode() {
		return mode;
	}
}