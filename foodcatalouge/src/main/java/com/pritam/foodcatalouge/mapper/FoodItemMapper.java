package com.pritam.foodcatalouge.mapper;

import com.pritam.foodcatalouge.dto.FoodItemDTO;
import com.pritam.foodcatalouge.entity.FoodItem;

public class FoodItemMapper {

	public static FoodItem dtoToFoodItem(FoodItemDTO dto)
	{
		return new FoodItem(dto.getId(), dto.getItemName(),dto.getItemDescription(),dto.isVeg(),dto.getPrice(),dto.getRestaurantId());
	}
	
	public static FoodItemDTO foodItemToDTO(FoodItem item)
	{
		return new FoodItemDTO(item.getId(), item.getItemName(),item.getItemDescription(),item.isVeg(),item.getPrice(),item.getRestaurantId());
	}
}
