package com.lcwd.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;

public interface RatingService {

	 
	
	
	// create
	Rating create(Rating rating);
	
	
	//get All Ratings
	List<Rating> getRatings();
	
	
	// get all rating by user
	List<Rating> getRatingByUserid(String userId);
	
	
	// get all rating by hotel
	List<Rating> getRatingByHotelid(String hotelId);

	
	
	
}
