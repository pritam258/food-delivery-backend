package com.pritam.foodcatalouge.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pritam.foodcatalouge.dto.Restaurant;



@FeignClient(name="RESTAURANT-SERVICE",url="http://localhost:9091")
public interface RestaurantService {
	@GetMapping("/{resId}")
	public Restaurant getRestaurant(@PathVariable Integer resId,@RequestHeader("Authorization") String jwt);

}
