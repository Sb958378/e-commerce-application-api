package com.retail.e_commerce.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
      
	  @Value("${myapp.jwt.secret}")
	  private String secret;
	  @Value("${myapp.jwt.access.expiration}")
	  private long accessExpiry;
	  @Value("${myapp.jwt.refresh.expiration}")
	  private long refreshExpiry;
	  
	  
	  public  String generateAccessToken(String username,  String role ) {
			return generateToken(username,  accessExpiry, role);
	    	   
	       }
	  
	  
	  public String generateRefreshToken(String username , String role) {
		  return generateToken(username ,  refreshExpiry ,role);
	  }
	  
	  public String getusername(String token)
		{
			return parseJwtClaims(token).getSubject();
		}
		public String getUserRole(String token)
		{
			return parseJwtClaims(token).get("role",String.class);
		}
	  
	  private String generateToken(String username , long expiration , String role) {
		return Jwts.builder().setClaims(Maps.of("role", role).build()).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
		 .setExpiration(new Date(System.currentTimeMillis()+ expiration))
		 .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
		 .compact();
		  
	  }
	  
	       private Key getSignatureKey() {
	    	   
			return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	    	   
	       }
	       
	       

	       private Claims parseJwtClaims(String token) {
			return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJwt(token).getBody();
	    	   
	       }


		

		

	       
	     
	       

		
}
