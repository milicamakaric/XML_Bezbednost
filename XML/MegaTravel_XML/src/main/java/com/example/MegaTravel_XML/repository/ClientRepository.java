package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.User;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findByEmail(String email);

}
