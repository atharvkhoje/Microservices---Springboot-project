package com.lcwd.rating.ratingserviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	
	@Autowired
	private RatingRepository ratingRepository;
	
	
	@Override
	public Rating create(Rating rating) {
 
		String randomUserId = UUID.randomUUID().toString();
		//User.setUserId(randomUserId);
		rating.setRatingId(randomUserId);
		
		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
 		return this.ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserid(String userId) {
 		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelid(String hotelId) {
		// TODO Auto-generated method stub
		return this.ratingRepository.findByHotelId(hotelId);
	}

}
