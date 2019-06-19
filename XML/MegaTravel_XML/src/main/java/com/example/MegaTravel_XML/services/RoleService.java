package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Role;


@Service
public interface RoleService {

	Role findByName(String string);

}
