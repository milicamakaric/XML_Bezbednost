package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Cancelation;

@Repository
public interface CancelationRepository extends JpaRepository<Cancelation, Long>{

}
