package com.moviecon.RegisterLogin.MainController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviecon.RegisterLogin.DetailInformation.RegisterationInformation;
import com.moviecon.RegisterLogin.MainService.RegisterationFormService;

import jakarta.validation.Valid;

@RestController
public class RegisterationFormController {
	
	@Autowired
	private RegisterationFormService RegisterationService;
	
	@PostMapping("/UserRegistrationInformation")
	public String UserRegistrationInformation(@Valid @RequestBody RegisterationInformation RegisterationDetail) {
		
		if( RegisterationService.EmailExistOrNot(RegisterationDetail) ) {
			
			return "Email already exist";
		}
		
		else {
			
			RegisterationService.AddRegistrationDetail(RegisterationDetail);
		}
		
		return "Registered";
	}

}
