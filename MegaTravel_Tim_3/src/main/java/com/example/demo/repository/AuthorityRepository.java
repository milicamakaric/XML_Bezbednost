package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findOneById(Long id);
	Authority findOneByName(String name);
}
