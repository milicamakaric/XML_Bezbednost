package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Message;

@Service
public interface MessageService {
	
	public void deleteAll();
	public Message save(Message message);

	List<Message> getAll();
	Message saveMessage(Message mess);
}	
