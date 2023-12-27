package com.moviecon.RegisterLogin.MainRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviecon.RegisterLogin.DetailInformation.RegisterationInformation;

@Repository
public interface RegisterationRepository extends CrudRepository<RegisterationInformation, String> {

	@Query(nativeQuery= true, value= "SELECT email_id FROM registeration_information WHERE email_id = :EmailId")
	public String EmailExistOrNot(@Param("EmailId") String EmailId);
	
	@Query(nativeQuery= true, value= "SELECT * FROM registeration_information WHERE email_id = :EmailId")
	public RegisterationInformation ManuelUsernamePassword(@Param("EmailId") String EmailId);
	
}