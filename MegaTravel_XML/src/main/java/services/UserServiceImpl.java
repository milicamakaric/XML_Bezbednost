package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

}
