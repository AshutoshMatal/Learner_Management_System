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


	public String Note_Not_Exist = "Note does not Exist";

	public String Note_UnPin = "You can Pin it again";

	public String Note_Pin = "You can UnPin it again";

	public String Note_Archive = "You can UnAchive it again";

	public String Note_UnArchive = "You can Archive it agin";

	public String Note_UnTrash = "Note is Successfully Restored !!!!";

	public String Note_Trash = "Note is Successfully Delete and Added into Trash !!!!";

	public String Note_Label_Not_Exist = "No Note with this Label";

	public String Note_Create = "Note is Successfully create for given User token";

	public String Note_Update = "Note is Update Successfully";

	public String Note_Delete = "Note is Permanently Deleted from Note List";

	public String Note_Not_Exist_User = "Note does not belong to User";

	public String Note_Not_Exist_In_Trash = "Note does not Exist in Trash";


}
