package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Agent;
import com.example.agent.model.Client;
import com.example.agent.model.User;

@Service
public interface UserService {

	public User findByEmail(String forHtml);
	public User findById(Long id);

	public Client saveClient(Client client);
	public Client findClientById(Long id);
	public Client findClientByEmail(String forHtml);
	public List<Client> getUsers();

	public Agent saveAgent(Agent agent);
	public Agent findAgentByEmail(String email);
	public List<Agent> getAllAgents();
	
	public void deleteClients(String client);
	public void deleteAgents(String agent);
	public void deleteUserRoles();
	public void deleteAccommodationAgent();
}
