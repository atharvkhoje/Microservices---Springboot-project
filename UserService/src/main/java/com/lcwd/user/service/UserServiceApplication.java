package com.lcwd.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import com.lcwd.rating.Bean;
//import com.lcwd.rating.RestTemplate;
 
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

		
		
		
		
	}

}
