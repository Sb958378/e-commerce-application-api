package com.retail.e_commerce.response_dto;

import java.util.List;

import com.retail.e_commerce.Entity.Contact;
import com.retail.e_commerce.enums.AddressType;

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
public class AddressResponse {
	
	
	
	
	 private int addressId;
	   private String streetAddress;
	   private String streetAddressAdditional;
	   private AddressType addressType;
	   private String city;
	   private String state;
	   private int pincode;
	   private List<ContactResponse> contacts;

}
