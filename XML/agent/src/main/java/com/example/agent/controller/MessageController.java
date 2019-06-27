package com.example.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.dto.MessageDTO;
import com.example.agent.model.Message;
import com.example.agent.model.SaveMessageResponse;
import com.example.agent.services.MessageService;
import com.example.agent.soap.UpdateClient;

@RestController
@RequestMapping(value="message")
@CrossOrigin(origins = "http://localhost:4202")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	private UpdateClient updateClient;
	
	
	
	@PreAuthorize("hasAuthority('getAgentMessages')")
	@RequestMapping(value="/getAgentMessages/{agent_id}", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getMessagesOfAgent(@PathVariable("agent_id") Long agent_id){
		
		System.out.println("Usao u kontroler za dobijanje poruka");
		List<Message> allMess = messageService.getAll();
		List<Message> agentMess = new ArrayList<Message>();
		for(int i=0;i<allMess.size();i++)
		{
			Message m = allMess.get(i);
			if(m.getAgent().getId().equals(agent_id) && m.isSending())
			{
				agentMess.add(m);
			}
		}
		
		System.out.println("Size " + agentMess.size());
		
		List<MessageDTO> messDtos = new ArrayList<MessageDTO>();
		for(Message m: agentMess)
		{
			MessageDTO md = new MessageDTO(m.getId(), m.getTitle(), m.getContent(), m.getClient().getId(), m.getClient().getName(), m.getClient().getSurname(), m.getAgent().getId(), m.getAgent().getName(), m.getAgent().getSurname(), m.isSending());
			messDtos.add(md);
		}
		
		return new ResponseEntity<List<MessageDTO>>(messDtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('sendAnswer')")
	@RequestMapping(value = "/sendAnswer", method = RequestMethod.POST)
	public ResponseEntity<?> addMessage(@RequestBody Message message) {
		System.out.println("usao da posalje odg");
		message.setSending(false);
		
		SaveMessageResponse response = updateClient.saveMessage(message);
		System.out.println("saved in response: " + response.isSaved());
		
		if(response.isSaved()) {
			System.out.println("poruka je sacuvana u glavnom beku");
			Message saved = messageService.saveMessage(response.getMessage());
			return new ResponseEntity<Message>(saved, HttpStatus.OK);
		}else {
			System.out.println("poruka nije sacuvana u glavnom beku");
			return  new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}
	
}
