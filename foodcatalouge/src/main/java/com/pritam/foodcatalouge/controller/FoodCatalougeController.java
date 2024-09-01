package com.pritam.foodcatalouge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pritam.foodcatalouge.dto.FoodItemDTO;
import com.pritam.foodcatalouge.dto.UserDTO;
import com.pritam.foodcatalouge.service.FoodCatalougeService;
import com.pritam.foodcatalouge.service.UserService;

@RestController
@RequestMapping("/api/foodCatalouge")
@CrossOrigin
public class FoodCatalougeController {
	
	@Autowired
	FoodCatalougeService foodCatalougeService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItemDTO foodItemDTO,@RequestHeader("Authorization") String jwt) throws Exception
	{
		UserDTO user=userService.getUserProfile(jwt);
		FoodItemDTO foodItemSaved = foodCatalougeService.createFoodItem(foodItemDTO,jwt);
		return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<List<FoodItemDTO>> getAllFoodItems(@PathVariable Integer restaurantId,@RequestHeader("Authorization") String jwt) throws Exception
	{
		List<FoodItemDTO> foodItems =foodCatalougeService.getAllFoodItemsByRestaurantId(restaurantId,jwt);
		return new ResponseEntity<>(foodItems,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<FoodItemDTO> getFoodItems(@PathVariable Integer id,@RequestHeader("Authorization") String jwt) throws Exception
	{
		FoodItemDTO foodItem =foodCatalougeService.getFoodById(id);
		return new ResponseEntity<>(foodItem,HttpStatus.OK);
	}

}
