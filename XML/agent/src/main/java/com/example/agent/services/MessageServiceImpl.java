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
	public List<Message> getAll() {
		
		return messageRepository.findAll();
	}

	@Override
	public Message saveMessage(Message mess) {
		// TODO Auto-generated method stub
		return messageRepository.save(mess);
	}

}
