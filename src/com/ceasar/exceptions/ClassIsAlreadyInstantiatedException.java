package com.ceasar.exceptions;

public class ClassIsAlreadyInstantiatedException extends RuntimeException {    
	private static final long serialVersionUID = 1L;
	
    public ClassIsAlreadyInstantiatedException() {
        super();
    }
    public ClassIsAlreadyInstantiatedException(final String message) {
        super(message);
    }
    public ClassIsAlreadyInstantiatedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    /**public static void main(String[] args) {
    	System.out.format("+---------+---------+---------+-------+%n");
    	System.out.format("| Name    | Height  |  Weight | BMI   |%n");
    	System.out.format("+---------+---------+---------+-------+%n");
    	String leftAlignment = "| %-7s | %-7.2f | %-7.2f | %-5.2f |%n";  
    	for (float  i :  new float[] {123.2f, 121.25f, 74.25f, 88.24f, 55.2f}) {
    	    System.out.format(leftAlignment, Float.valueOf(i), i - 20.024f, i + 12, i * 5);
    	    System.out.format("+---------+---------+---------+-------+%n");
    	}
	}*/
}
