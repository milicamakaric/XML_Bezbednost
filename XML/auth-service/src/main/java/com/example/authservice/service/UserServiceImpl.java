package com.example.authservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByEmail(String username) {
		List <User> svi = userRepository.findAll();
		for(User u: svi) {
			System.out.println("prvi jue "+u.getUsername());
		}
		System.out.println("dobio ej "+username);
		return userRepository.findByEmail(username);
	}

	
}
