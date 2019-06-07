package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.MessageRepository;

public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

}
