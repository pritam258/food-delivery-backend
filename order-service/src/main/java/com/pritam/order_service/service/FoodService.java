package com.pritam.order_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pritam.order_service.dto.FoodItemsDTO;

@FeignClient(name="FOOD-SERVICE",url="http://localhost:9090")
public interface FoodService {
	@GetMapping("/api/foodCatalouge/{id}")
	public FoodItemsDTO getFoodItems(@PathVariable Integer id,@RequestHeader("Authorization") String jwt);

}
