package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.repository.MessageRepository;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public List<Message> getAll() {
		return messageRepository.findAll();
	}
	
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

}
