package com.pritam.order_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer id;
	private Integer userId;
	private Integer restaurantId;
	private Integer foodItemId;
	private Integer quantity;

}
