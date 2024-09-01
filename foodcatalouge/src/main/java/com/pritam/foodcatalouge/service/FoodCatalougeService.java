package com.pritam.foodcatalouge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pritam.foodcatalouge.dto.FoodItemDTO;
import com.pritam.foodcatalouge.dto.Restaurant;
import com.pritam.foodcatalouge.entity.FoodItem;
import com.pritam.foodcatalouge.mapper.FoodItemMapper;
import com.pritam.foodcatalouge.repo.FoodItemRepo;

@Service
public class FoodCatalougeService {
	
	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	RestaurantService restaurantService;

	
	public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO,String jwt) throws Exception
	{
		int resId=foodItemDTO.getRestaurantId();
		Restaurant restaurant=restaurantService.getRestaurant(resId, jwt);
		if(restaurant!=null)
		{
		
		   FoodItem foodItemSavedInDB = foodItemRepo.save(FoodItemMapper.dtoToFoodItem(foodItemDTO));
		   return FoodItemMapper.foodItemToDTO(foodItemSavedInDB);
		}
		throw new Exception("Restaurant is not prsent with id "+resId);
	}
	
	public List<FoodItemDTO> getAllFoodItemsByRestaurantId(Integer resId,String jwt) throws Exception
	{
		Restaurant restaurant=restaurantService.getRestaurant(resId, jwt);
		if(restaurant!=null)
		{
			List<FoodItem> foodItems=foodItemRepo.findByRestaurantId(resId);
			List<FoodItemDTO> foodItemsDTO=foodItems.stream().map((item)->FoodItemMapper.foodItemToDTO(item)).collect(Collectors.toList());
	        return foodItemsDTO;
		}
		throw new Exception("Restaurant is not prsent with id "+resId);
	}
	
	public FoodItemDTO getFoodById(Integer id) throws Exception
	{
		FoodItem foodItem=foodItemRepo.findById(id).orElseThrow(()->new Exception("food item is not present with id "+id));
		return FoodItemMapper.foodItemToDTO(foodItem);
	}
}

