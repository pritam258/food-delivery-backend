package com.pritam.restaurantlisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.restaurantlisting.dto.RestaurantDTO;
import com.pritam.restaurantlisting.dto.UserDTO;
import com.pritam.restaurantlisting.service.RestaurantService;
import com.pritam.restaurantlisting.service.UserService;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO,@RequestHeader("Authorization") String jwt) throws Exception
	{
		UserDTO user=userService.getUserProfile(jwt);
		RestaurantDTO createRes=restaurantService.createRestaurant(restaurantDTO,user.getRole());
		return new ResponseEntity<RestaurantDTO>(createRes,HttpStatus.CREATED);
	}
	@PutMapping("/{resId}")
	public ResponseEntity<RestaurantDTO> updateRestauirant(@RequestBody RestaurantDTO restaurantDTO,@PathVariable Integer resId,@RequestHeader("Authorization") String jwt) throws Exception
	{
		UserDTO user=userService.getUserProfile(jwt);
		RestaurantDTO updateRes=restaurantService.updateRestaurant(restaurantDTO,resId,user.getRole());
		return new ResponseEntity<RestaurantDTO>(updateRes,HttpStatus.OK);
	}
	
	@DeleteMapping("/{resId}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable Integer resId,@RequestHeader("Authorization") String jwt) throws Exception
	{
		UserDTO user=userService.getUserProfile(jwt);
		restaurantService.deleteRestaurant(resId,user.getRole());
		return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/{resId}")
	public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Integer resId,@RequestHeader("Authorization") String jwt)
	{
		RestaurantDTO getRes= restaurantService.getRestaurant(resId);
		return new ResponseEntity<>(getRes,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RestaurantDTO>> getRestaurants(@RequestHeader("Authorization") String jwt)
	{
		List<RestaurantDTO> getRests= restaurantService.getRestaurants();
		return new ResponseEntity<>(getRests,HttpStatus.OK);
	}
}
