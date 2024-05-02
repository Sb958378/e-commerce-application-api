package com.retail.e_commerce.response_dto;

import com.retail.e_commerce.enums.AddressType;
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
public class ContactResponse {
	
	private int contactId;
	private String contactName;
	private long phoneNumber;
	private String email;
	private Priority priority;

}
