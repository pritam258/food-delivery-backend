package com.pritam.restaurantlisting.mapper;

import com.pritam.restaurantlisting.dto.RestaurantDTO;
import com.pritam.restaurantlisting.model.Restaurant;

public class RestaurantMapper {
	
	public static Restaurant dtoToRestaurant(RestaurantDTO dto)
	{
		return new Restaurant(dto.getRestaurantId(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getRestaurantDescription());
	}
	public static RestaurantDTO restaurantToDto(Restaurant res)
	{
		return new RestaurantDTO(res.getRestaurantId(),res.getName(),res.getAddress(),res.getCity(),res.getRestaurantDescription());
	}

}
