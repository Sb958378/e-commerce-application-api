package com.retail.e_commerce.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class UserNotLoggedInException  extends RuntimeException{
	
	public UserNotLoggedInException(String message) {
		super();
		this.message = message;
	}

	private String message;
	 

}
