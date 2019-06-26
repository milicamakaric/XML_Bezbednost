package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.agent.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	@Transactional
	@Modifying
	@Query(value="DELETE FROM role_permissions",  nativeQuery = true)
	void deleteRolePermission();
}
