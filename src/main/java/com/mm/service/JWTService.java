package com.mm.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private String secretkey = "";
	
	public JWTService() {
			
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			
			SecretKey key = keyGen.generateKey();
			
			System.out.println(key);
			
			secretkey = Base64.getEncoder().encodeToString(key.getEncoded());
			
			System.out.println(secretkey);
		} 
		catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		
	}
	
	
	
	public String generateToken(String username) {
		
		
		Map<String, Object> claims = new HashMap<>();
		
		System.out.println("-------------------------");
		
		System.out.println(claims);  		//Printing
		
		System.out.println("-------------------------");
		
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
				.and()
				.signWith(getKey())
	            .compact();
				 
				
	}
	
	
	
	private SecretKey getKey() {
		
		byte[] decode = Decoders.BASE64.decode(secretkey);
		
		return Keys.hmacShaKeyFor(decode);
	}
	
	
	public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
	
	
	private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

	public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

	

}






























