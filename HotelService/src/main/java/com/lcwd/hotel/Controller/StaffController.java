package com.lcwd.hotel.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

	@GetMapping
	public ResponseEntity<List<String>> getStaff() 
	{
		List<String> list = Arrays.asList("ram","shyam","krishna");
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
