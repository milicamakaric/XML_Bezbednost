package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.Agent;

	@Service
	public interface UserService {
	
	public List<Client> getUsers();
	public Agent saveAgent(Agent agent);
	public Client saveClient(Client client);
	public Client findClientById(long id);
}
