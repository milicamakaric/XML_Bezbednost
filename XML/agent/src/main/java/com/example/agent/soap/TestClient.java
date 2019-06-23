package com.example.agent.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.agent.model.test.TestRequest;
import com.example.agent.model.test.TestResponse;

public class TestClient extends WebServiceGatewaySupport {
	
	public TestResponse getTest(String label) {

		TestRequest request = new TestRequest();
		request.setLabel(label);
		
		System.out.println("getTest in TestClient entered");

		TestResponse response = (TestResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);
//						new SoapActionCallback(
//								"http://megatravel.com/soap"));

		return response;
	}


}
