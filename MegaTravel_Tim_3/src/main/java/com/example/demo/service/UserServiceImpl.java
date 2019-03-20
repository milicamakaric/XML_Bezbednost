package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    
	@Override
	public User findOneById(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

}
