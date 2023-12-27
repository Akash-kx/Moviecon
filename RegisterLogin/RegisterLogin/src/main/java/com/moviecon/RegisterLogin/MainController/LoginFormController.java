package com.moviecon.RegisterLogin.MainController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviecon.RegisterLogin.DetailInformation.LoginInformation;
import com.moviecon.RegisterLogin.JWTtoken.JwtUtil;

@RestController
public class LoginFormController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/AddLoginInformation")
	public String AddLoginInformation(@RequestBody LoginInformation LoginDetail) {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(LoginDetail.getUserEmail(), LoginDetail.getUserPassword(), new ArrayList<>()));
		}
		catch( Exception ex ) {
			
			return "Not valid username or password";
		}
		
		return jwtUtil.generateToken(LoginDetail.getUserEmail());
	}

}
