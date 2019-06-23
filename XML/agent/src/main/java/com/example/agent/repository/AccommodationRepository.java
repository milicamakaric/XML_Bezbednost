package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


	 Accommodation findById(long id);
	
}
