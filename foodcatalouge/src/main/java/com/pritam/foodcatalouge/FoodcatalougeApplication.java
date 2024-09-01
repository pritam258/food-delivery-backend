package com.pritam.foodcatalouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableFeignClients
public class FoodcatalougeApplication {


	public static void main(String[] args) {
		SpringApplication.run(FoodcatalougeApplication.class, args);
		
		//@Bean
		//public RestTemplate restTemplate()
		//{
			//return new RestTemplate();
		//}
		
	
	}

}
