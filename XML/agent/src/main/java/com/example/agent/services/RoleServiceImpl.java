package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Role;
import com.example.agent.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String string) {
		
		return roleRepository.findByName(string);
	}

}
