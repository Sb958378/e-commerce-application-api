package com.retail.e_commerce.Exception;

public class OtpInvalidException extends RuntimeException {

	private String message;

	public OtpInvalidException(String message) {
	
		this.message = message;
	}

	public String getMessage() {
		return message;
	}



	


}
