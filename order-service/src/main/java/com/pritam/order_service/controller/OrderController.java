package com.pritam.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.order_service.dto.OrderDTO;
import com.pritam.order_service.dto.OrderDTOFromFE;
import com.pritam.order_service.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO, @RequestHeader("Authorization") String jwt)
	{
		OrderDTO orderSavedInDB = orderService.createOrder(orderDTO, jwt);
		return new ResponseEntity<>(orderSavedInDB, HttpStatus.CREATED);
	}
	
	

}
