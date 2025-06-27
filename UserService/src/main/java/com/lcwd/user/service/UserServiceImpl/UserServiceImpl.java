package com.lcwd.user.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Ratings;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.externals.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	private HotelService hotelService;
	
	
	@Override
	public User saveUser(User user) {
 
		String randomUserId = UUID.randomUUID().toString();
		//User.setUserId(randomUserId);
		user.setUserId(randomUserId);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
 
		return this.userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
 
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server" + userId));
		
		// fetch rating of the above user from RATING SERVICE
		//http://localhost:8083/ratings/getRatingByUserId/dfc46650-3c88-4dfb-aa84-a72b9d501445
		
		  Ratings[] ratingsofUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/getRatingByUserId/"+user.getUserId(), Ratings[].class);
		 // we are using JSON array, you must pass Ratings[].class. because Java does not allow generic type literals like List<Ratings>.class.
		 // So you must use an array class (Ratings[].class) for deserialization.
		
		 // it will look like this 
			/*
			 * Ratings[] ratingsofUser = { new Ratings("r1", "abc-123", "h1", 5,
			 * "Excellent stay", null), new Ratings("r2", "abc-123", "h2", 4,
			 * "Good experience", null) };
			 */

		 
		 
		 
		 
		logger.info("{} ",ratingsofUser);
		
		
	//	Converts a Java array (Ratings[]) to a List<Ratings>`.
	//	Easier to work with using stream(), map(), filter(), etc.
		List<Ratings> ratings = Arrays.stream(ratingsofUser).toList(); // with the help of this we are converting java array to list
		
		List<Ratings> ratingList = ratings.stream().map(rating->
		{
			 
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
 			
			// set the hotel to reating
			rating.setHotel(hotel);
						
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		 
		return user;
	}

	
	
}
