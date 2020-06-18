package com.lms.message;

import org.springframework.stereotype.Component;

@Component
public class MessageInfo {
	public String Bad_Request = "400";

	public String Success_Request = "200";

	public String Redirect_Request = "300";



	public String Invalide_Token = "Invalide Token";

	public String User_Exist = "Please try with other Email-id";

	public String Registration_Done = "Befor Login Please Verify Email-id ";

	public String Login_Done = "Loged-In Successfully";

	public String Invalide_Password = "Password is incorrect";

	public String User_Not_Exist = "Please try with another Email-id";

	public String Token_Send = "Please verify Token for Forget Password";

	public String User_Not_Verify = "Please first Verify Email-id by token";

	public String Update_Password = "User can login with new Password";

	public String Verify_User = "User can Login Successfully";

	public String Update_Status= "Update Successfully";
	
	public String Iilegal_Request="IIlegal Request";
	
	public String Imported_Success="Imported Successfully";

	}
