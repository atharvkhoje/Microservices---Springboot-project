package com.lcwd.user.service.externals.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.lcwd.user.service.entities.Ratings;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {

	
	// post
	@PostMapping("/ratings/create")
	public Ratings createRating(Ratings values);
	
	 
	
	
}
