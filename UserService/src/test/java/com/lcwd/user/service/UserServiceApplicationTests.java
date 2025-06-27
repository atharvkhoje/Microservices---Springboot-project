package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.entities.Ratings;
import com.lcwd.user.service.externals.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
//	@Test
	/*void createRating()
	{
		Ratings rating = Ratings.builder().rating(10).userId("").hotelId("").feedback("for test purpose").build();
		Ratings rating2 = ratingService.createRating(rating);
		
		System.out.println("new rating created");
	
	}*/

}
