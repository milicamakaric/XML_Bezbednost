package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface UserService extends UserDetailsService{
	User findOneById(Long id);
	User saveUser(User user);
	void removeUser(Long id);
	User findUserByMail(String mail);
	List<User> getAll();
	
	
}
