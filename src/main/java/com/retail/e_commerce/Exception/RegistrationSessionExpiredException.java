package com.retail.e_commerce.Exception;

public class RegistrationSessionExpiredException  extends RuntimeException{
   private String message;



public String getMessage() {
	return message;
}



public RegistrationSessionExpiredException(String message) {
	
	this.message = message;
}
   
   
}
