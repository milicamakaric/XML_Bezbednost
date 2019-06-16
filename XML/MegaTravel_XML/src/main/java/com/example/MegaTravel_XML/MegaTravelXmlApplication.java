package com.example.MegaTravel_XML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MegaTravelXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaTravelXmlApplication.class, args);
	}

}
