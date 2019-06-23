package com.example.agent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.example.agent.model.Accommodation;
import com.example.agent.model.test.Test;
import com.example.agent.soap.AccommodationClient;
import com.example.agent.soap.MessageClient;
import com.example.agent.soap.TestClient;

@Configuration
public class AgentConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		System.out.println("package name: " + Accommodation.class.getPackage().getName());
		//marshaller.setContextPath(Accommodation.class.getPackage().getName());
		marshaller.setContextPath(Test.class.getPackage().getName());
		return marshaller;
	}

	@Bean
	public AccommodationClient accommodationClient(Jaxb2Marshaller marshaller) {
		System.out.println("AccommodationClient in AgentConfig entered");
		AccommodationClient client = new AccommodationClient();
		client.setDefaultUri("http://megatravelxml/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public MessageClient messageClient(Jaxb2Marshaller marshaller) {
		System.out.println("MessageClient in AgentConfig entered");
		MessageClient client = new MessageClient();
		client.setDefaultUri("http://megatravelxml/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public TestClient testClient(Jaxb2Marshaller marshaller) {
		System.out.println("TestClient in AgentConfig entered");
		TestClient client = new TestClient();
		client.setDefaultUri("http://megatravelxml/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
