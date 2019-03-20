package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    
	@Override
	public User findOneById(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
	@Override
	public User findUserByMail( String mail) {
		// TODO Auto-generated method stub
		System.out.println("Usao u findUserbyMail");
		return repository.findOneByEmail(mail);
	}

}
