package com.example.agent.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.agent.model.Message;
import com.example.agent.model.MessageRequest;
import com.example.agent.model.MessageResponse;

public class MessageClient extends WebServiceGatewaySupport {
	
	public MessageResponse getMessage(Message message) {

		MessageRequest request = new MessageRequest();
		request.setEmail("mail.test@gmail.com");
		
		System.out.println("getMessage in MessageClient entered");

		MessageResponse response = (MessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);
//						new SoapActionCallback(
//								"http://megatravel.com/soap"));

		return response;
	}

}
