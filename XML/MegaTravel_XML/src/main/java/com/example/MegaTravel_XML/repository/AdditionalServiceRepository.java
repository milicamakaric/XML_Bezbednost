package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MegaTravel_XML.model.AdditionalService;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long>{

	AdditionalService findByName(String name);

}
