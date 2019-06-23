package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agent.model.AccommodationType;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long>{

	
	AccommodationType findByName(String name);

}
