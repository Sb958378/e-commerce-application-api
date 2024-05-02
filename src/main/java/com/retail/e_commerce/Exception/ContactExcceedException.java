package com.retail.e_commerce.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContactExcceedException extends RuntimeException{

	private  String message;
	
}
