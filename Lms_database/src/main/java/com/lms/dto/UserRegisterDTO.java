package com.lms.dto;


//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;

public class UserRegisterDTO 
{
	//VARIABLES
//	@Email(message = "Please provide a valid e-mail")
//	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	//@NotEmpty(message = "Please provide your user name")
	private String first_name;
	//@NotEmpty(message = "Please provide your name")
	private String last_name;
	//@NotEmpty(message = "Please provide your name")
	private String password;
	//@Pattern(regexp = "([0-9]{10})")
	private long contact_number;
	//@NotEmpty(message = "Please provide your name")
	private String creator_user;
	//CONSTRUCTOR
//	public UserRegisterDTO(
//			//@Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail")
//			String email,
//			//@NotEmpty(message = "Please provide your user name") 
//			String first_name,
//			//@NotEmpty(message = "Please provide your name")
//			String last_name,
//			//@NotEmpty(message = "Please provide your name") 
//			String password,
//			//@Pattern(regexp = "([0-9]{10})")
//			long contact_number,
//			//@NotEmpty(message = "Please provide your name")
//			String creator_user) {
//		super();
//		this.email = email;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.password = password;
//		this.contact_number = contact_number;
//		this.creator_user = creator_user;
//	}
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

