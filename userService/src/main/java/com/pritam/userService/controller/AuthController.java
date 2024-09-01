package com.pritam.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.userService.config.JwtProvider;
import com.pritam.userService.dto.UserDTO;
import com.pritam.userService.entity.User;
import com.pritam.userService.repo.UserRepo;
import com.pritam.userService.request.LoginRequest;
import com.pritam.userService.response.AuthResponse;
import com.pritam.userService.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserServiceImplementation customUserDetails;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserDTO userDTO) throws Exception{
		String email=userDTO.getEmail();
		String name=userDTO.getName();
		String password=userDTO.getPassword();
		String address=userDTO.getAddress();
		String role=userDTO.getRole();
		
		User isEmailExist=userRepo.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new Exception("Email is already user with another account");
		}
		
		User createdUser=new User();
		createdUser.setName(name);
		createdUser.setEmail(email);
		createdUser.setAddress(address);
		createdUser.setRole(role);
		createdUser.setPassword(passwordEncoder.encode(password));
		User savedUser=userRepo.save(createdUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=JwtProvider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Register succes");
		authResponse.setStatus(true);
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest)
	{
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
	
		Authentication authentication=authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=JwtProvider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Login succes");
		authResponse.setStatus(true);
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}
	
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails=customUserDetails.loadUserByUsername(username);
		
		if(userDetails==null)
		{
			throw new BadCredentialsException("Invalid username or password");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}
	
	

}
