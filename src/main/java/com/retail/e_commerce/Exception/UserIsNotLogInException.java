package com.retail.e_commerce.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserIsNotLogInException extends RuntimeException {
	
	private String message;
	

}
