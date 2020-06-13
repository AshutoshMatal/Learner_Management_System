package com.lms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.ForgetPasswordDTO;
import com.lms.dto.ResetPasswordDTO;
import com.lms.dto.UserLoginDTO;
import com.lms.dto.UserRegisterDTO;
import com.lms.model.Response;
import com.lms.service.UserServiceImpl;
@RequestMapping("/user")
@RestController
public class UserController{
	@Autowired 
	UserServiceImpl userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserRegisterDTO userRegisterDto)throws Exception,NullPointerException{
		Response response=userService.register(userRegisterDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK); 
	}
	@PostMapping("/login")
	public  ResponseEntity<Response> login(@RequestBody UserLoginDTO userLoginDto)
	{
		Response response=userService.login(userLoginDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/forget")
	public ResponseEntity<Response> forget(@RequestBody ForgetPasswordDTO forgetPasswordDto){
		Response response=userService.forget(forgetPasswordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@PutMapping("/reset/{token}")
	public ResponseEntity<Response> reset(@PathVariable String token, @RequestBody ResetPasswordDTO resetPasswordDto){
		Response response=userService.reset(token, resetPasswordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	 @GetMapping("/validateUser/{token}")
	public ResponseEntity<String> validateUser(@PathVariable String token)
	{
		Response response=userService.validateUser(token);
		return new ResponseEntity<String>(response.getMessage(), HttpStatus.OK);
	}
	 
}
