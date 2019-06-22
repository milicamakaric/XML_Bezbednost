package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agent.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String string);

}
