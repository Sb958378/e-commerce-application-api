package com.retail.e_commerce.security;


import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail.e_commerce.jwt.JwtFilter;
import com.retail.e_commerce.jwt.JwtService;
import com.retail.e_commerce.userRepo.AccessRepo;
import com.retail.e_commerce.userRepo.RefreshRepo;

import lombok.AllArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private JwtService jwtService;
	private AccessRepo accessRepo;
	private RefreshRepo refreshRepo;
	private UserDetailsService userDetailservice;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12); //more secured and more use and 12 times hashing is basic secure
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailservice);
		return provider;
	}
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth->
		auth.requestMatchers("/api/v1/login","api/v1/register","api/v1/verify-otp").permitAll().anyRequest().authenticated())
				.sessionManagement(management -> management
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(new JwtFilter(accessRepo, refreshRepo, jwtService), UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	@Bean
	 AuthenticationManager atuthencationManager(AuthenticationConfiguration congfiguration) throws Exception{
		return congfiguration.getAuthenticationManager();
	}
	   
}
