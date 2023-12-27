package com.moviecon.InventoryProduct.JWTtoken;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenValidation extends OncePerRequestFilter {
	
    @Autowired
    private JwtUtil jwtUtil;
    
    private String JwtToken;
    private String UserEmail;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String RequestHeader = request.getHeader("Authorization");
        
        if ( RequestHeader != null && RequestHeader.startsWith("Bearer ") ) {
        	
        	this.JwtToken = RequestHeader.substring(7);
        	this.UserEmail = jwtUtil.usernameInToken(JwtToken);
        }
        
        if ( this.UserEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ) {

            if ( !jwtUtil.tokenDateExpiredOrNot(this.JwtToken) ) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(this.UserEmail, null, new ArrayList<>());
                
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }

}