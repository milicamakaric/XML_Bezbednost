package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Role;
import com.example.MegaTravel_XML.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String string) {
		
		return roleRepository.findByName(string);
	}

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

}
