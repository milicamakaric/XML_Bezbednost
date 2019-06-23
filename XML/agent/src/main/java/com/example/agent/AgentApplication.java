package com.example.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.example.agent.model.Message;
import com.example.agent.model.MessageResponse;
import com.example.agent.soap.MessageClient;

@SpringBootApplication
@EnableEurekaClient
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}
/*
	@Autowired MessageClient quoteClient;
	
	@Bean
	CommandLineRunner lookup() {
		return args -> {
			String country = "Spain";

			if (args.length > 0) {
				System.out.println("args length: " + args.length);
				country = args[0];
			}
			MessageResponse response = quoteClient.getMessage(new Message());
			System.err.println(response.getMessage());
		};
	}
*/
}
