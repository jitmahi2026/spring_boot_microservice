package com.api.gateway.apigateway.wbSecurityConfig;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";
	
	public boolean validateToken(String token)
	{
		try {
			
			Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes())).build().parseSignedClaims(token);	
			
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}

}
