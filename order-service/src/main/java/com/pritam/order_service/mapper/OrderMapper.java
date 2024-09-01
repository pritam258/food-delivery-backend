package com.pritam.order_service.mapper;

import com.pritam.order_service.dto.OrderDTO;
import com.pritam.order_service.entity.Order;

public class OrderMapper {
	
	public static OrderDTO OrdertoDTO(Order order)
	{
		return new OrderDTO(order.getId(),order.getUserId(),order.getRestaurantId(),order.getFoodItemId(),order.getQuantity());
	}

	public static Order dtoToOrder(OrderDTO dto)
	{
		return new Order(dto.getId(),dto.getUserId(),dto.getRestaurantId(),dto.getFoodItemId(),dto.getQuantity());
	}
}
