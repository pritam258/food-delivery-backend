package com.pritam.userService.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pritam.userService.config.JwtProvider;
import com.pritam.userService.dto.UserDTO;
import com.pritam.userService.entity.User;
import com.pritam.userService.exceptions.ResourceNotFoundException;
import com.pritam.userService.mapper.UserMapper;
import com.pritam.userService.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public UserDTO createUser(UserDTO userDTO)
	{
		User savedUser = userRepo.save(UserMapper.dtoToUser(userDTO));
		return UserMapper.userToDto(savedUser);
	}
	
	public UserDTO updateUser(UserDTO userDTO, Integer userId)
	{
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		User updatedUser = userRepo.save(UserMapper.dtoToUser(userDTO));
		return UserMapper.userToDto(updatedUser);
	}
	
	public UserDTO getUserById(Integer userId)
	{
		User user =userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return UserMapper.userToDto(user);
	}
	
	public List<UserDTO> getAllUsers()
	{
		List<User> users = userRepo.findAll();
		return users.stream().map(user->UserMapper.userToDto(user)).collect(Collectors.toList());
	}
	
	public void deleteUser(Integer userId)
	{
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);
	}
	
	public UserDTO getUserProfile(String jwt)
	{
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepo.findByEmail(email);
		return UserMapper.userToDto(user);
	}

}
