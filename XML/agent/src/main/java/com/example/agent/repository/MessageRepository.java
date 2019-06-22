package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

}
