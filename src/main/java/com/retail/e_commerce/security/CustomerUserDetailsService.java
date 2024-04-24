package com.retail.e_commerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.retail.e_commerce.userRepo.UserRepo;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService{

	    private UserRepo userRepo;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			return userRepo.findByUsername(username).map(CustomerUserDetails::new)// method implementation reference

					.orElseThrow(() -> new UsernameNotFoundException("user not found"));

		}

	    
	
	 
	

}
