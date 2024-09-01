package com.pritam.order_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOFromFE {
	
	private List<FoodItemsDTO> foodItemsList;
	private Integer userId;
	private Restaurant restaurant;

}
