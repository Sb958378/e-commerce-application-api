package com.retail.e_commerce.Exception;

public class InvalidRequestException  extends RuntimeException{
	
	private String message;

	public InvalidRequestException(String message) {
		super();
		this.message = message;
	}
	

}
