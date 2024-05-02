package com.retail.e_commerce.request_dto;

import com.retail.e_commerce.Entity.Contact;
import com.retail.e_commerce.enums.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {
	
	
	 private String contactName;
	    private long contactNumber;
	 private Priority priority;
		
}
