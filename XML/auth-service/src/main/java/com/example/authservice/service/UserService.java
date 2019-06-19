package com.example.authservice.service;

import org.springframework.stereotype.Service;

import com.example.authservice.model.User;


@Service
public interface UserService {

	User getUserByEmail(String username);
	
	

}
