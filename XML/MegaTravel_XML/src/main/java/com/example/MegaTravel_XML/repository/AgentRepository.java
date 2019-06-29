package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{
	 Agent findByEmail(String email);
}
