package com.lcwd.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


 
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	

	// create user
	@PostMapping("/createUser")
	public ResponseEntity<User> createuser(@RequestBody User user)
	{
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	int retryCount=1;
	// single user get
	@GetMapping("/getuserById/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
 	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		logger.info("retry count: {}",retryCount);
		retryCount++;
		User user = this.userService.getUser(userId);
		return ResponseEntity.ok(user);
 
	}
	
	// creating fallback method for circuit breaker
	
	 
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
	{
		//logger.info("Fallback is executed because service is down: ",ex.getMessage());
		 
		User user = User.builder()
		.email("dummy@gmail.com")
		.name("dummy")
		.about("this user is dummy because some service is down")
		.userId("1234")
		.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	//all user get
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUser = this.userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
}
