package com.retail.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.e_commerce.jwt.JwtService;
import com.retail.e_commerce.request_dto.AuthRequest;
import com.retail.e_commerce.request_dto.OtpRequest;
import com.retail.e_commerce.request_dto.UserRequest;
import com.retail.e_commerce.response_dto.AuthResponse;
import com.retail.e_commerce.response_dto.UserResponse;
import com.retail.e_commerce.service.AuthService;
import com.retail.e_commerce.util.ResponseStructure;
import com.retail.e_commerce.util.SimpleResponseStructure;

import lombok.AllArgsConstructor;




@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {
	
	
	private AuthService authService;
	private JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<SimpleResponseStructure> registerUser(@RequestBody UserRequest userRequest){
		
		return authService.registerUser(userRequest);
		
		
		
		
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(@RequestBody  OtpRequest otpRequest){
		
		return authService.verifyOTP(otpRequest);
		
	}
//	
//	@GetMapping("/access")
//	public String accessToken(String username ,String role) {
//		return jwtService.generateAccessToken("dsssas" ,"role");
//		
//	}
//	
//	@GetMapping("/refresh")
//	public String refreshToken(String username , String role) {
//		return jwtService.generateRefreshToken("dadffss" , "role");
//		
//	}
  
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
		   
		return authService.login(authRequest );
	}
	  
}

