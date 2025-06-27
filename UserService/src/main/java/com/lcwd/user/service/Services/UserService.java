package com.lcwd.user.service.Services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	// create
	User saveUser(User user);
	
	// get all user
	List<User> getAllUser();
	
	// get single user from USerId
	User getUser(String userId);
	
	
	// delete
	
	
	// update
	
	
	
	
}
