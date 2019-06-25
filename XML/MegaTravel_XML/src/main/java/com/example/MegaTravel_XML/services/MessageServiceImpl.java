package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.repository.MessageRepository;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

	public Message saveMessage(Message message) {
		// TODO Auto-generated method stub
		return messageRepository.save(message);
	}

}
