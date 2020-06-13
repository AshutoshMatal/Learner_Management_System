package com.lms.dto;
public class ForgetPasswordDTO 
{
	private String email;
	private long contact_number;
	public ForgetPasswordDTO() {
		super();
	}
	public ForgetPasswordDTO(String email, long contact_number) {
		super();
		this.email = email;
		this.contact_number = contact_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContact_number() {
		return contact_number;
	}
	public void setContact_number(long contact_number) {
		this.contact_number = contact_number;
	}
	
	}