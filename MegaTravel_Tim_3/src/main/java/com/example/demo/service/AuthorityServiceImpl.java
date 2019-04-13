package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Authority;
import com.example.demo.repository.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityRepository repository;
	
	@Override
	public Authority findOneById(Long id) {
		
		return repository.findOneById(id);
	}

	@Override
	public Authority findOneByName(String name) {
		return repository.findOneByName(name);
	}

	@Override
	public Authority saveAuthority(Authority a) {
		return repository.save(a);
	}

}
