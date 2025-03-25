package com.ceasar.encryption.interfaces;

public enum EncodingType {
    BASE64("base64"),
    HEX("hex");
	
	private EncodingType(final String encodingType) {
		this.encodingType = encodingType;
	}
	private String encodingType;
	
	public String getEncodingType() {
		return encodingType;
	}
}