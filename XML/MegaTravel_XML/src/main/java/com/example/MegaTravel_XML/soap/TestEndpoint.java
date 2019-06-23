package com.example.MegaTravel_XML.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.test.Test;
import com.example.MegaTravel_XML.model.test.TestRequest;
import com.example.MegaTravel_XML.model.test.TestResponse;

@Endpoint
public class TestEndpoint {
	
	private static final String NAMESPACE_URI = "http://megatravel.com/test";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "TestRequest")
    @ResponsePayload
    public TestResponse getTest(@RequestPayload TestRequest request) {
		TestResponse response = new TestResponse();
		
		System.out.println("getTest in TestEndpoint; request label: " + request.getLabel());
		
		Test test1 = new Test();
		test1.setLabel("label1");
		test1.setNumber(1);
		
		Test test2 = new Test();
		test2.setLabel("label2");
		test2.setNumber(2);
		
		response.getTest().add(test1);
		response.getTest().add(test2);
 
        return response;
    }

}
