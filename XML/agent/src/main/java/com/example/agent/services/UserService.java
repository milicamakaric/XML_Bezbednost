package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Agent;
import com.example.agent.model.Client;
import com.example.agent.model.User;

@Service
public interface UserService {
	
	public List<Client> getUsers();
	public Agent saveAgent(Agent agent);

	public Client findClientByEmail(String forHtml);

	public User findByEmail(String forHtml);
	public User findById(Long id);

	public Client saveClient(Client client);
	
	public Client findClientById(Long id);

	public Agent findAgentByEmail(String email);
	public List<Agent> getAllAgents();
}
