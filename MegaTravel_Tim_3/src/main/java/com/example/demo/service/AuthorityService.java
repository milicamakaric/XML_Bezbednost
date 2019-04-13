package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Authority;

@Service
public interface AuthorityService {
	Authority findOneById(Long id);
	Authority findOneByName(String name);
	Authority saveAuthority(Authority a);
}
