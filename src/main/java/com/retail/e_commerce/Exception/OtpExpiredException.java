package com.retail.e_commerce.Exception;

public class OtpExpiredException extends RuntimeException {
	private String message;

	public OtpExpiredException(String message) {
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
