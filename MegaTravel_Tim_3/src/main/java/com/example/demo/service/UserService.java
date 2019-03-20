package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface UserService {
	User findOneById(Long id);
	User saveUser(User user);
	void removeUser(Long id);
	User findUserByMail(String mail);
	
	
}
