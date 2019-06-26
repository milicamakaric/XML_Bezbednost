package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Permission;

@Service
public interface PermissionService {
	
	public List<Permission> getAll();

}
