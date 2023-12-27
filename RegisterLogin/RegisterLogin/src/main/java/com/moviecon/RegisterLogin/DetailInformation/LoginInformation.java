package com.moviecon.RegisterLogin.DetailInformation;

public class LoginInformation {
	
	private String userEmail;
	
	private String userPassword;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LoginInformation(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public LoginInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginInformation [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
	
}
