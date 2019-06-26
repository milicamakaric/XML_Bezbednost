package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Role;


@Service
public interface RoleService {

	public Role findByName(String string);
	public void deleteAll();
	public Role save(Role role);

}
