package com.retail.e_commerce.Exception;

public class UserAlreadyPresentException extends RuntimeException{

	
	private String message;

	public UserAlreadyPresentException(String message) {
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
