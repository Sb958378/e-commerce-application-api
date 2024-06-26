package com.retail.e_commerce.service;


import org.springframework.http.ResponseEntity;

import com.retail.e_commerce.request_dto.AuthRequest;
import com.retail.e_commerce.request_dto.OtpRequest;
import com.retail.e_commerce.request_dto.UserRequest;
import com.retail.e_commerce.response_dto.AuthResponse;
import com.retail.e_commerce.response_dto.UserResponse;
import com.retail.e_commerce.util.ResponseStructure;
import com.retail.e_commerce.util.SimpleResponseStructure;



public interface AuthService {
	

		
	public ResponseEntity<SimpleResponseStructure> registerUser(UserRequest userRequest);
	public  ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest);
	 public ResponseEntity<ResponseStructure<AuthResponse>> login(AuthRequest authRequest);
	
	
}
