package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Role;


@Service
public interface RoleService {

	Role findByName(String string);

}
