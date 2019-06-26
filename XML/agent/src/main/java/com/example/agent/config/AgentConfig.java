package com.example.agent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.example.agent.model.GetMessageRequest;
import com.example.agent.soap.AccommodationClient;
import com.example.agent.soap.BaseClient;
import com.example.agent.soap.TestClient;

@Configuration
public class AgentConfig {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		System.out.println("package name: " + GetMessageRequest.class.getPackage().getName());
		marshaller.setContextPath(GetMessageRequest.class.getPackage().getName());
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
	public TestClient testClient(Jaxb2Marshaller marshaller) {
		System.out.println("TestClient in AgentConfig entered");
		TestClient client = new TestClient();
		client.setDefaultUri("http://megatravelxml/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public BaseClient baseClient(Jaxb2Marshaller marshaller) {
		System.out.println("baseClient in AgentConfig entered");
		BaseClient baseClient = new BaseClient();
		baseClient.setDefaultUri("http://megatravelxml/ws");
		baseClient.setMarshaller(marshaller);
		baseClient.setUnmarshaller(marshaller);
		return baseClient;
	}
}
