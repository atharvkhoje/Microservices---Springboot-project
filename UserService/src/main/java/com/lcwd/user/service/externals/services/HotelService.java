package com.lcwd.user.service.externals.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {

	
	@GetMapping("/hotel/getById/{hotelId}")
	public Hotel getHotel(@PathVariable String hotelId);
	
	
	
	
}
