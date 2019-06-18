package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


	public Accommodation findById(long id);
}