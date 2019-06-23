package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{
	 Agent findByEmail(String email);
	 
}
