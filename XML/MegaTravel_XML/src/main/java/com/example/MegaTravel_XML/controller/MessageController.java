package com.example.MegaTravel_XML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.services.MessageServiceImpl;

@RestController
@RequestMapping(value="api/message")
@CrossOrigin(origins = "http://localhost:4201")
public class MessageController {
	
	@Autowired
	private MessageServiceImpl messageService;
	
	@PreAuthorize("hasAuthority('sendMessage')")
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ResponseEntity<?> addMessage(@RequestBody Message message) {
		System.out.println("usao da posalje odg");
		message.setSending(false);
		Message saved = messageService.saveMessage(message);
		return new ResponseEntity<Message>(saved, HttpStatus.OK);

	}

}
