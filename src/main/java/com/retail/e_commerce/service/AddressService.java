package com.retail.e_commerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.retail.e_commerce.request_dto.AddressRequest;
import com.retail.e_commerce.request_dto.ContactRequest;
import com.retail.e_commerce.response_dto.AddressResponse;
import com.retail.e_commerce.response_dto.ContactResponse;
import com.retail.e_commerce.util.ResponseStructure;

public interface AddressService {
	
	
	            
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest ,String accesstoken);
    public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressbyUser(String accessToken, String refreshToken);
	public ResponseEntity<ResponseStructure<AddressResponse>>updateAddress(int addressId, AddressRequest addressRequest );
	public ResponseEntity<ResponseStructure<ContactResponse>>addContact(int  addressId, ContactRequest contactRequest);
	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(int contactId,ContactRequest contactRequest);
}
