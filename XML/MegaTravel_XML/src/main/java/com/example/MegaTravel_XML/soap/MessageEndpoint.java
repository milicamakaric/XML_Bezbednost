package com.example.MegaTravel_XML.soap;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.MessageRequest;
import com.example.MegaTravel_XML.model.MessageResponse;
import com.example.MegaTravel_XML.services.MessageService;

@Endpoint
public class MessageEndpoint {
	
	private static final String NAMESPACE_URI = "http://megatravel.com/soap";
	
	@Autowired 
	private MessageService messageService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MessageRequest")
    @ResponsePayload
    public MessageResponse getMessage(@RequestPayload MessageRequest request) {
		MessageResponse response = new MessageResponse();
		
		Message message = new Message();
		message.setContent("test message");
		message.setDate(new Date());
		message.setTitle("test title");
		
		response.setMessage(message);
 
        return response;
    }

}
