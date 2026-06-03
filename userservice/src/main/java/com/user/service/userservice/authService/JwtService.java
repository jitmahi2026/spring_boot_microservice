package com.user.service.userservice.authService;

import java.util.Date;


import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";
	
	public String generateToken(String userName)
	{
		 return Jwts.builder()
	                .subject(userName)
	                .issuedAt(new Date())
	                .expiration(
	                        new Date(System.currentTimeMillis()
	                                + 86400000))
	                .signWith(
	                        Keys.hmacShaKeyFor(
	                                SECRET.getBytes()),
	                        Jwts.SIG.HS256)
	                .compact();
						 
	}
	
}
