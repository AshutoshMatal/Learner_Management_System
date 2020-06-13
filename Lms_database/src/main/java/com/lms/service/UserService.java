package com.lms.service;
import com.lms.dto.ForgetPasswordDTO;
import com.lms.dto.ResetPasswordDTO;
import com.lms.dto.UserLoginDTO;
import com.lms.dto.UserRegisterDTO;
import com.lms.model.Response;


public interface UserService 
{	
	Response login(UserLoginDTO userLoginDto);
	Response register(UserRegisterDTO userRegistrationDto)throws Exception,NullPointerException;
	Response forget(ForgetPasswordDTO forgetPasswordDto);
	Response reset(String token,ResetPasswordDTO resetPasswordDto);
	Response validateUser(String token);
	
}


