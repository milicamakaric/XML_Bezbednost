package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MegaTravel_XML.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String string);

}
