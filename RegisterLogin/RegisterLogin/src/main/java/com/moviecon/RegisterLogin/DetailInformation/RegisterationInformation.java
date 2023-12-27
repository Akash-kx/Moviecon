package com.moviecon.RegisterLogin.DetailInformation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class RegisterationInformation {

	@Id
	@Column(name="emailId", columnDefinition="VARCHAR(64)" )
	@Email(message="Enter a valid email id")
	@NotBlank(message="Can not be null")
	private String emailId;
	
	@NotBlank(message="Can not be null")
	private String userName;
	
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$" ,message="Minimum length 8 must have number and uppercase, lowercase letters")
	private String userPassword;
	
	@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$" ,message="Phone number is invalid")
	private String userPhoneNumber;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public RegisterationInformation(
			@Email(message = "Enter a valid email id") @NotBlank(message = "Can not be null") String emailId,
			@NotBlank(message = "Can not be null") String userName,
			@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Minimum length 8 must have number and uppercase, lowercase letters") String userPassword,
			@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", message = "Phone number is invalid") String userPhoneNumber) {
		super();
		this.emailId = emailId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhoneNumber = userPhoneNumber;
	}

	public RegisterationInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RegisterationInformation [emailId=" + emailId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userPhoneNumber=" + userPhoneNumber + "]";
	}
	
}
