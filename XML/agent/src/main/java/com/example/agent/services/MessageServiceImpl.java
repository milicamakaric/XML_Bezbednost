package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Message;
import com.example.agent.repository.MessageRepository;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void deleteAll() {
		messageRepository.deleteAll();
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

}
