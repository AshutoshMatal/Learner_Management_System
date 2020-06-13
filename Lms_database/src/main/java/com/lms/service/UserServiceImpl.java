package com.lms.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lms.dto.ForgetPasswordDTO;
import com.lms.dto.ResetPasswordDTO;
import com.lms.dto.UserLoginDTO;
import com.lms.dto.UserRegisterDTO;
import com.lms.exception.ForgetPasswordException;
import com.lms.exception.LoginException;
import com.lms.exception.RegistrationException;
import com.lms.exception.ResetPasswordException;
import com.lms.exception.ValidateException;
import com.lms.message.JwtToken;
import com.lms.message.MessageInfo;
import com.lms.message.MessageResponse;
import com.lms.model.Response;
import com.lms.model.User;
import com.lms.repository.UserRepository;



@Service
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment environment;
	@Autowired
	private MessageInfo message;

	@Autowired
	private JwtToken jwtObject;

	@Autowired
	private MessageResponse messageResponse;

	SimpleMailMessage emailId;

	@Autowired
	private ModelMapper mapper;



	@Override
	public Response login(UserLoginDTO userLoginDto) 
	{
		User user = userRepository.findByEmail(userLoginDto.getEmail());
		

		// USER PRESENT OR NOT
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		// CHECK VALIDATION
		if (user.getVerified()) 
		{
			if ((user.getPassword()).equals(userLoginDto.getPassword())) 
			{
			
				String token = jwtObject.generateToken(userLoginDto.getEmail());
				return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
						environment.getProperty("status.login.success"),token);
			} 
			else 
			{
				return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
						environment.getProperty("status.password.incorrect"), message.Invalide_Password);
			}
		}
		else
		{
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"), message.User_Not_Verify);
		}
	}

	@Override
	public Response register(UserRegisterDTO userRegisterDto)throws Exception,NullPointerException
	{
		String checkEmail = userRegisterDto.getEmail();
		User userIsPresent = userRepository.findByEmail(checkEmail);
		// CHECK USER PRESENT OR NOT
		if (userIsPresent != null) 
			throw new RegistrationException(400,message.User_Exist);
		User user = mapper.map(userRegisterDto, User.class);
		String token = jwtObject.generateToken(user.getEmail());
		System.out.println(token);
		emailId= messageResponse.verifyMail(user.getEmail(), user.getFirst_name(), token);
		emailService.sendEmail(emailId);
		userRepository.save(user);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("status.user.register"), message.Registration_Done);
	}

	@Override
	public Response forget(ForgetPasswordDTO forgetPasswordDto)
	{
		User user = userRepository.findByEmail(forgetPasswordDto.getEmail());
		// CHECK USER PRESENT OR NOT
		if (user == null)
			throw new ForgetPasswordException(400,message.User_Exist);
		// VALIDATION 
		if (user.getVerified())
		{
			String token = jwtObject.generateToken(forgetPasswordDto.getEmail());
			User userData = userRepository.findByEmail(forgetPasswordDto.getEmail());
			System.out.println(token);
			emailId = messageResponse.passwordReset(forgetPasswordDto.getEmail(), user.getFirst_name(), token);
			emailService.sendEmail(emailId);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.token.send"), message.Token_Send);
		} 
		else
		{
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"),message.User_Not_Verify);
		}
	}


	@Override
	public Response reset(String token, ResetPasswordDTO resetPasswordDto) 
	{
		String checkEmail = jwtObject.getToken(token);
		User userUpdate = userRepository.findByEmail(checkEmail);
		//System.out.println(user);
		// USER PRESENT OR NOT
		if (userUpdate == null)
			throw new ResetPasswordException(400,message.User_Exist);
		// CHECK PASSWORD AND CONFIRM ARE EQUALS OR NOT
		if (resetPasswordDto.getConfirmPassword().equals(resetPasswordDto.getPassword()))
		{
			userUpdate.setPassword(resetPasswordDto.getPassword());
			userRepository.save(userUpdate);
			
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.password.update"),token);
		}
		else 
		{
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("status.password.incorrect"), message.Bad_Request);
		}
	}

	@Override
	public Response validateUser(String token) 
	{
		String email = jwtObject.getToken(token);
		User userIsVerified = userRepository.findByEmail(email);
		// USER PRESENT OR NOT
		if (userIsVerified == null)
			throw new ValidateException(400,message.User_Not_Exist);
		userIsVerified.setVerified(true);
		userRepository.save(userIsVerified);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("status.email.isverify"), message.Verify_User);
	}

}