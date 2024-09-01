package com.pritam.order_service.entity;

import java.util.List;

import com.pritam.order_service.dto.FoodItemsDTO;
import com.pritam.order_service.dto.Restaurant;
import com.pritam.order_service.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer userId;
	private Integer restaurantId;
	private Integer foodItemId;
	private Integer quantity;


}
