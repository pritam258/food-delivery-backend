package com.pritam.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pritam.order_service.dto.FoodItemsDTO;
import com.pritam.order_service.dto.OrderDTO;
import com.pritam.order_service.dto.OrderDTOFromFE;
import com.pritam.order_service.dto.Restaurant;
import com.pritam.order_service.dto.UserDTO;
import com.pritam.order_service.entity.Order;
import com.pritam.order_service.mapper.OrderMapper;
import com.pritam.order_service.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired 
	OrderRepo orderRepo;

	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	UserService userService;
	
	public OrderDTO createOrder(OrderDTO orderDTO,String jwt)
	{
		Restaurant restaurant =restaurantService.getRestaurant(orderDTO.getRestaurantId(), jwt);
		UserDTO user=userService.getSingleUser(orderDTO.getUserId());
		FoodItemsDTO foodItem=foodService.getFoodItems(orderDTO.getFoodItemId(), jwt);
		
		Order order=new Order();
		order.setFoodItemId(foodItem.getId());
		order.setQuantity(orderDTO.getQuantity());
		order.setRestaurantId(restaurant.getId());
		order.setUserId(user.getUserId());
		Order savedOrder=orderRepo.save(order);
		return OrderMapper.OrdertoDTO(savedOrder);
		
	}
	
	
	
}
