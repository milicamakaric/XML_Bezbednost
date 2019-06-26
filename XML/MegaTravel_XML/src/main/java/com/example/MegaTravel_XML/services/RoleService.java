package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Role;


@Service
public interface RoleService {

	public Role findByName(String string);
	public List<Role> getAll();
}
