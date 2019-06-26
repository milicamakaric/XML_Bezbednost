package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Permission;
import com.example.MegaTravel_XML.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<Permission> getAll() {
		return permissionRepository.findAll();
	}

	

}
