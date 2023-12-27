package com.moviecon.RegisterLogin.JWTtoken;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${secret.key}")
	private String secretKey;

    public String generateToken(String userEmail) {
    	
    	HashMap<String, Object> Claims = new HashMap<String, Object>();
    	return createToken(Claims, userEmail);
    }

    private String createToken(Map<String, Object> claims, String userEmail) {
    	
    	return Jwts.builder()
    			.setClaims(claims)
    			.setSubject(userEmail)
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis() + 1000*60))
    			.signWith(SignatureAlgorithm.HS256, secretKeyEncode())
    			.compact();
    }

    private Key secretKeyEncode() {
    	
    	byte[] specKey = Base64.getEncoder().encode(secretKey.getBytes());
    	SecretKey secretKey = new SecretKeySpec(specKey, SignatureAlgorithm.HS256.getJcaName());
    	return secretKey;
	}
	
    public Claims extractAllClaims(String jwtToken) {
    	
    	return Jwts.parser()
    			.setSigningKey(secretKeyEncode())
    			.parseClaimsJws(jwtToken)
    			.getBody();
    }

}
