package com.retail.e_commerce.request_dto;

import com.retail.e_commerce.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
	
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	private String name;
	
	@NotBlank(message = "email should be not null")
	@NotNull(message = "userEmail should not be null")
	
	@Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+(com|org|in|co|net|com\\.au|com\\.com)", message = "email is not valid please enter correct email")
	// ^[a-z0-9\\.\\_\\-\\+a-z0-9]*\\@[a-z0-9]*\\.(com|org|in|co|net|com\\.au|com\\.com)
	private String email;
	@NotNull(message = "userPassword should not be null")
	@Size(min = 8, max = 16, message = "password should be length is 8>= &&<=16")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "give properly password")
	private String password;
	
	private UserRole userRole;


	
	

}
