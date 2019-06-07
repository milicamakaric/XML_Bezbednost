package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

}
