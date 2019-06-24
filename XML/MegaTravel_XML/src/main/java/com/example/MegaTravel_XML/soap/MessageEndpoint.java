package com.example.MegaTravel_XML.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.MessageRequest;
import com.example.MegaTravel_XML.model.MessageResponse;
import com.example.MegaTravel_XML.services.MessageService;
import com.example.MegaTravel_XML.services.UserService;

@Endpoint
public class MessageEndpoint {
	
	private static final String NAMESPACE_URI = "http://megatravel.com/message";
	
	@Autowired 
	private MessageService messageService;
	
	@Autowired 
	private UserService agentService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MessageRequest")
    @ResponsePayload
    public MessageResponse getMessage(@RequestPayload MessageRequest request) {
		
		System.out.println("getMessage in MessageEndpoint entered; agent_id: " + request.getAgentId());
		
		MessageResponse response = new MessageResponse();
		
		Message message = new Message();
		Agent agent = (Agent) agentService.findById(3);
		Client client = (Client) agentService.findById(2);
		
		message.setContent("test message");
		message.setTitle("test title");
		message.setSending(false);
		message.setAgent(agent);
		message.setClient(client);
		
		response.getMessage().add(message);
 
        return response;
    }

}
