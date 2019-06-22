package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agent.model.AdditionalService;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long>{

	AdditionalService findByName(String name);

}
