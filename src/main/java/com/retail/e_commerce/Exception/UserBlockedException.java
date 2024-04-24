package com.retail.e_commerce.Exception;

public class UserBlockedException extends RuntimeException {
	
	private String message;

	public UserBlockedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
