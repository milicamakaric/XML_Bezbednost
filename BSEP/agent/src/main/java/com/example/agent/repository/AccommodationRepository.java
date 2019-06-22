package com.example.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.agent.model.Accommodation;


@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


	 Accommodation findById(long id);
	
	 @Transactional
	 @Query(value="SELECT * FROM Accommodation accommodation WHERE accommodation.agent.id = ?1 ",  nativeQuery = true)
	 List<Accommodation> getByAgentId(Long id);
}	
