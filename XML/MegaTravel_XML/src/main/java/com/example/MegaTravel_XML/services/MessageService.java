package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Message;


@Service
public interface MessageService {
	
	public List<Message> getAll();
	public Message save(Message message);

}
