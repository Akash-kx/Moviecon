package com.moviecon.ApiGateway.JwtTokenValidation;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenValidationLogic {
	
	@Value("${secret.key}")
	private String secretKey;

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
    
    public String usernameInToken(String jwtToken) {

    	Claims allClaims = extractAllClaims(jwtToken);
    	String userEmail = allClaims.getSubject();
    	
    	return userEmail;
    }
    
    public boolean tokenDateExpiredOrNot(String jwtToken) {

    	Claims allClaims = extractAllClaims(jwtToken);
    	return allClaims.getExpiration().before(new Date());
    }

}
