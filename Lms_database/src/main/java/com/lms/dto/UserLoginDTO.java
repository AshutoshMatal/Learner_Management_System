package com.lms.dto;
public class UserLoginDTO 
{
	//VARIABLES
	String email;
	String password;
	//CONSTRUCTOR
	public UserLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	//GETTERS AND SETTERS
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}



