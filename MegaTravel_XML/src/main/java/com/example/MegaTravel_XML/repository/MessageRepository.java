package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.message.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

}
