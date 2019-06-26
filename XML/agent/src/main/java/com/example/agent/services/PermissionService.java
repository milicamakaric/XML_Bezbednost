package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Permission;

@Service
public interface PermissionService {
	
	public void deleteAll();
	public Permission save(Permission permission);
	public void deleteRolePermission();

}
