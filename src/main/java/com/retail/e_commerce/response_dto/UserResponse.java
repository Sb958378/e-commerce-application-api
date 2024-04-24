package com.retail.e_commerce.response_dto;

import com.retail.e_commerce.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponse {
   
	 
	private int userId;
	private String displayName;
	private String userName;
	private String email;
	private UserRole userRole;
	private boolean isEmailVerfied;
	

}
