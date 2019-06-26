package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.MegaTravel_XML.model.User;



@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	User findByEmail(String email);
	User findById(long id);
	
}
