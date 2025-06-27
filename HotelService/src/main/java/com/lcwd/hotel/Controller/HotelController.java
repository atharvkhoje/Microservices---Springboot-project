package com.lcwd.hotel.Controller;

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

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	
	// create
	@PreAuthorize("hasAuthority('ADMIN') ")
	@PostMapping("/create")
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel)
	{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	// get hotel by id
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/getById/{hotelid}")
	public ResponseEntity<Hotel> get(@PathVariable String hotelid)
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelid));
	}
	
	
	// get all
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN') ")
	@GetMapping("/getAll")
	public ResponseEntity<List<Hotel>> getAll()
	{
		return ResponseEntity.ok(hotelService.getAll());
	}
	
	
	
	
	
	
}
