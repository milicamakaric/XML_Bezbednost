package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.MessageRepository;

public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

}
