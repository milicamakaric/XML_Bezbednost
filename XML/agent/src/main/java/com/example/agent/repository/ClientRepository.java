package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{


	Client findByEmail(String email);


}
