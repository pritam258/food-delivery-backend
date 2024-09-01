package com.pritam.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	
    private int id;
	
	private String name;
	private String email;
	private String password;
	private String address;
	private String role;
}
