package com.example.agent.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="api/agent")
@CrossOrigin(origins = "http://localhost:4201")
public class AgentController {
	
	@RequestMapping(
			value = "/communicate",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> communicate(@RequestBody String message) throws Exception{
		System.out.println("Usao u communicate "+ message);
		
		RequestEntity<Object> requestEntity = null;
		
        RestTemplate template = new RestTemplate();
        return template.exchange("https://localhost:8443/api/users/communication", HttpMethod.GET, requestEntity, String.class);
	}

}
