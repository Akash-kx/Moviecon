package com.moviecon.RegisterLogin.SpringSecurityConfiguration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.moviecon.RegisterLogin.DetailInformation.RegisterationInformation;
import com.moviecon.RegisterLogin.MainRepository.RegisterationRepository;

@Component
public class ManuelUsernamePasswordAuthentication implements AuthenticationProvider{
	
	private String userName;
	
	private String userPassword;
	
	@Autowired
	private RegisterationRepository RegisterationFormRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		this.userName = authentication.getName();
		this.userPassword = authentication.getCredentials().toString();
		
		RegisterationInformation RegisterationDetail =  RegisterationFormRepository.ManuelUsernamePassword(userName);
		
		if( userName.equals(RegisterationDetail.getEmailId()) && userPassword.equals(RegisterationDetail.getUserPassword()) ) {

			return new UsernamePasswordAuthenticationToken(userName, userPassword, new ArrayList<>());
		}
		else {
	
			throw new NullPointerException("NO VALID");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
