package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findOneById(Long id);
	
	@Query(value="select * from User user where user.email=?1", nativeQuery=true)
	User findOneByEmail(String mail);
	
}
