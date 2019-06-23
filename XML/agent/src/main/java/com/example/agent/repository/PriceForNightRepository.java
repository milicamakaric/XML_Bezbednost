package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.PriceForNight;



@Repository
public interface PriceForNightRepository extends JpaRepository<PriceForNight, Long>{

}
