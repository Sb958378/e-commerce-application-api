package com.retail.e_commerce.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.retail.e_commerce.Exception.UserBlockedException;
import com.retail.e_commerce.userRepo.AccessRepo;
import com.retail.e_commerce.userRepo.RefreshRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private AccessRepo accessRepo;
	private RefreshRepo refreshRepo;
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String at = null;
		String rt = null;
		 if(request.getCookies() != null) {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("at")) at = cookie.getValue();
		   if (cookie.getName().equals("rt")) rt = cookie.getValue();
			
		}
	}
		if (at != null && rt != null) {

			// access token is blocked or not

			if (!accessRepo.existsByTokenAndIsBlocked(at, true)
					&& refreshRepo.existsByTokenAndIsBlocked(at, false)) {
				        new UserBlockedException("user blocked");
			}
			String UserName = jwtService.getusername(at);
			String userRole = jwtService.getUserRole(at);
			if(UserName!= null && SecurityContextHolder.getContext().getAuthentication()!=null) {
             new UsernamePasswordAuthenticationToken(UserName, userRole,
            		 Collections.singleton(new SimpleGrantedAuthority(userRole)))
            		 .setDetails(new WebAuthenticationDetails(request));
            
			}
		}
		filterChain.doFilter(request, response);

	}

}
