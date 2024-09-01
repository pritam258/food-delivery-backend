package com.pritam.foodcatalouge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	private int restaurantId;
	private String name;
	private String address;
	private String city;
	private String restaurantDescription;


}
