package com.moviecon.RegisterLogin.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviecon.RegisterLogin.DetailInformation.RegisterationInformation;
import com.moviecon.RegisterLogin.MainRepository.RegisterationRepository;

@Service
public class RegisterationFormService {

	@Autowired
	private RegisterationRepository RegisterationFormRepository;
	
	public boolean EmailExistOrNot(RegisterationInformation RegisterationDetail) {
		
		String ExistOrNot = RegisterationFormRepository.EmailExistOrNot(RegisterationDetail.getEmailId());
		
		if(ExistOrNot == null) {
			
			return false;
		}
		
		return true;
	}
	
	public void AddRegistrationDetail(RegisterationInformation RegisterationDetail) {
		
		if(RegisterationDetail != null) {
			
			RegisterationFormRepository.save(RegisterationDetail);
		}
		
	}
}