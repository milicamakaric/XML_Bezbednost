package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.User;

@Service
public interface UserService {
	
	public List<Client> getUsers();
	public Agent saveAgent(Agent agent);

	public Client findClientByEmail(String forHtml);

	public User findByEmail(String forHtml);
	public User findById(long id);

	public Client saveClient(Client client);
	
	public Client findClientById(Long id);

	public Agent findAgentByEmail(String email);
	public List<Agent> getAllAgents();
	public Agent findById(Long id);
}
