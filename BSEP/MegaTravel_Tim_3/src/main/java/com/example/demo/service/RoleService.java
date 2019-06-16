package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Role;

@Service
public interface RoleService {
	
	Role findByName(String name);

}
