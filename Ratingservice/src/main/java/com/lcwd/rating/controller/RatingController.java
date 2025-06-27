package com.lcwd.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	
	@Autowired
	private RatingService ratingService;
	
	@PreAuthorize("hasAuthority('ADMIN') ")
	@PostMapping("/create")
	public ResponseEntity<Rating> create(@RequestBody Rating rating)
	{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	// all rating
	
	@GetMapping("/getAllRatings")
	public ResponseEntity<List<Rating>> getRatings()
	{
		return ResponseEntity.ok(ratingService.getRatings());
	}
	
	// give rating by User
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN') ")
	@GetMapping("/getRatingByUserId/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
	{
		return ResponseEntity.ok(ratingService.getRatingByUserid(userId));
	}
	
	// give rating by hotel
	
	@GetMapping("/getRatingByHotelId/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
	{
		return ResponseEntity.ok(ratingService.getRatingByHotelid(hotelId));
	}
	
	
	
}
