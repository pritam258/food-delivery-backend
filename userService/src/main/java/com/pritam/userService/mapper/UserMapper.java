package com.pritam.userService.mapper;

import com.pritam.userService.dto.UserDTO;
import com.pritam.userService.entity.User;

public class UserMapper {
	
	public static User dtoToUser(UserDTO dto)
	{
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(),dto.getAddress(),dto.getRole());
	}
	public static UserDTO userToDto(User user)
	{
		return new UserDTO(user.getId(),user.getName(), user.getEmail(), user.getPassword(),user.getAddress(),user.getRole());
	}

}
