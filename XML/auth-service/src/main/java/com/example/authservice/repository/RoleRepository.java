package com.example.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authservice.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{

}
