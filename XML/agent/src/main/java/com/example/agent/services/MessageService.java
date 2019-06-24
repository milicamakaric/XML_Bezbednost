package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Message;

@Service
public interface MessageService {

	List<Message> getAll();

}
