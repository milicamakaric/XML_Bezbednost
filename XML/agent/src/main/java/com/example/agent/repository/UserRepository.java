package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.agent.model.User;




@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	User findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM User WHERE dtype = ?1",  nativeQuery = true)
	void deleteClients(String client);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM User WHERE dtype = ?1",  nativeQuery = true)
	void deleteAgents(String agent);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM user_roles",  nativeQuery = true)
	void deleteUserRoles();
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM accommodation_agent",  nativeQuery = true)
	void deleteAccommodationAgent();

}
