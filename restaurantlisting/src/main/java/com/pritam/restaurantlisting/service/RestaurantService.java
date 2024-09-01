package com.pritam.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pritam.restaurantlisting.dto.RestaurantDTO;
import com.pritam.restaurantlisting.exceptions.ResourceNotFoundException;
import com.pritam.restaurantlisting.mapper.RestaurantMapper;
import com.pritam.restaurantlisting.model.Restaurant;
import com.pritam.restaurantlisting.repo.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO,String requestRole) throws Exception
	{
		if(!requestRole.equals("ROLE_ADMIN")) {
			throw new Exception("only admin can create restaurant");
		}
		Restaurant savedRest=restaurantRepository.save(RestaurantMapper.dtoToRestaurant(restaurantDTO));
		return RestaurantMapper.restaurantToDto(savedRest);
	}
	
	
	public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, Integer restaurantId, String requestRole) throws Exception
	{
		if(!requestRole.equals("ROLE_ADMIN")) {
			throw new Exception("only admin can update restaurant");
		}
		Restaurant res = restaurantRepository.findById(restaurantId)
				.orElseThrow(()->new ResourceNotFoundException("Restaurant","Restaurant Id",restaurantId));
		res.setAddress(restaurantDTO.getAddress());
		res.setName(restaurantDTO.getName());
		res.setCity(restaurantDTO.getCity());
		res.setRestaurantDescription(restaurantDTO.getRestaurantDescription());
		Restaurant updatedres = restaurantRepository.save(res);
		return RestaurantMapper.restaurantToDto(updatedres);
		
	}
	public void deleteRestaurant(Integer restaurantId,String requestRole) throws Exception
	{
		if(!requestRole.equals("ROLE_ADMIN")) {
			throw new Exception("only admin can create restaurant");
		}
		Restaurant res = restaurantRepository.findById(restaurantId)
				.orElseThrow(()->new ResourceNotFoundException("Restaurant","Restaurant Id",restaurantId));
		restaurantRepository.delete(res);
		
	}
	
	public RestaurantDTO getRestaurant(Integer restaurantId)
	{
		Restaurant res = restaurantRepository.findById(restaurantId)
				.orElseThrow(()->new ResourceNotFoundException("Restaurant","Restaurant Id",restaurantId));
		return RestaurantMapper.restaurantToDto(res);
	}
	
	public List<RestaurantDTO> getRestaurants()
	{
		List<Restaurant> restaurants = restaurantRepository.findAll();
		List<RestaurantDTO> resDTOs=restaurants.stream().map((cat)->RestaurantMapper.restaurantToDto(cat)).collect(Collectors.toList());
		return resDTOs;
	}
	

}
