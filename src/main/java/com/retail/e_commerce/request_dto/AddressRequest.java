package com.retail.e_commerce.request_dto;

import com.retail.e_commerce.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AddressRequest {
	
	private String streetAddress;
	   private String streetAddressAdditional;
	   private AddressType addressType;
	   private String city;
	   private String state;
	   private int pincode;
	 
	 

}
