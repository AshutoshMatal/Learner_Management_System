package com.lms.dto;
public class ForgetPasswordDTO 
{
	private String email;
	private long mobileNo;
	public ForgetPasswordDTO(String email, long mobileNo) 
	{
		super();
		this.email = email;
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
}