package com.retail.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.retail.e_commerce.request_dto.AddressRequest;
import com.retail.e_commerce.request_dto.ContactRequest;
import com.retail.e_commerce.response_dto.AddressResponse;
import com.retail.e_commerce.response_dto.AuthResponse;
import com.retail.e_commerce.response_dto.ContactResponse;
import com.retail.e_commerce.service.AddressService;

import com.retail.e_commerce.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class AddressController {
	
	private AddressService addressService;
	@PostMapping("/add-address")
	 public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody AddressRequest addressRequest,
            @CookieValue(name = "at" , required = false) String accessToken){
		return addressService.addAddress(addressRequest,accessToken);
		
	}

	@GetMapping("/address/{addressId}")
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressbyUser(@CookieValue(name = "at",required = false)String accesToken,
			@CookieValue(name = "rt",required = false)String refreshToken)
	{
		return addressService.findAddressbyUser(accesToken,refreshToken);
	}
	@PutMapping("update/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@PathVariable int addressId,
			@ RequestBody AddressRequest addressRequest){
		return addressService.updateAddress(addressId, addressRequest);
	}
   @PostMapping("/contact/{addressId}")
	public  ResponseEntity<ResponseStructure<ContactResponse>>addContact(@PathVariable int addressId , @RequestBody ContactRequest contactRequest){
					return addressService.addContact(addressId, contactRequest);
		 
	 }
   @PutMapping("/contact/{contactId}")
       public ResponseEntity<ResponseStructure<ContactResponse>>updateContact(@PathVariable int contactId , @RequestBody ContactRequest contactRequest){
		return addressService.updateContact(contactId, contactRequest);
    	   
       }
}
