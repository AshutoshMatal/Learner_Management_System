package com.lms.dto;
public class UserRegisterDTO 
{
	//VARIABLES
	private String email;

	private String first_name;

	private String last_name;

	private String password;

	private long contact_number;

	private String creator_user;

	//CONSTRUCTOR
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContact_number() {
		return contact_number;
	}
	public void setContact_number(long contact_number) {
		this.contact_number = contact_number;
	}
	public String getCreator_user() {
		return creator_user;
	}
	public void setCreator_user(String creator_user) {
		this.creator_user = creator_user;
	}
}

