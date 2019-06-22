package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

}
