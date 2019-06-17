package com.example.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authservice.model.Permission;

@Repository
public interface PermissionRepository  extends JpaRepository<Permission,Long>{

}
