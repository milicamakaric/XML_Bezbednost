package com.example.agent.services;

import java.util.List;

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
	
	public List<Message> getAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message saveMessage(Message mess) {
		return messageRepository.save(mess);
	}

}
