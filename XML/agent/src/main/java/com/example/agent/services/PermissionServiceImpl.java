package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Permission;
import com.example.agent.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void deleteAll() {
		permissionRepository.deleteAll();
	}

	@Override
	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public void deleteRolePermission() {
		permissionRepository.deleteRolePermission();
	}

}
