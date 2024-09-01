package com.pritam.foodcatalouge.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pritam.foodcatalouge.dto.UserDTO;



@FeignClient(name="USER-SERVICE",url="http://localhost:9092")
public interface UserService {
	@GetMapping("/api/user/profile")
	public UserDTO getUserProfile(@RequestHeader("Authorization") String jwt);
	
	

}
