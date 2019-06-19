package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Agent;

@Service
public interface UserService {
	public Agent saveAgent(Agent agent);

}
