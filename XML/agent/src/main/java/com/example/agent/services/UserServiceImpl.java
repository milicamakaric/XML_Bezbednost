package com.example.agent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Agent;
import com.example.agent.model.Client;
import com.example.agent.model.User;
import com.example.agent.repository.AgentRepository;
import com.example.agent.repository.ClientRepository;
import com.example.agent.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Client findClientByEmail(String forHtml) {
		return clientRepository.findByEmail(forHtml);
	}


	@Override
	public User findByEmail(String forHtml) {
		return userRepository.findByEmail(forHtml);
	}

	
	@Override
	public List<Client> getUsers() {
		return clientRepository.findAll();
	}
	
	@Override
	public Agent saveAgent(Agent agent) {
		return agentRepository.save(agent);
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}


	@Override
	public Client findClientById(Long id) {
		return clientRepository.findById(id).get();
	}


	@Override
	public Agent findAgentByEmail(String email) {
		return agentRepository.findByEmail(email);
	}


	@Override
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}


	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}


	@Override
	public void deleteClients(String client) {
		userRepository.deleteClients(client);
	}


	@Override
	public void deleteAgents(String agent) {
		userRepository.deleteAgents(agent);
	}


	@Override
	public void deleteUserRoles() {
		userRepository.deleteUserRoles();
	}


	@Override
	public void deleteAccommodationAgent() {
		userRepository.deleteAccommodationAgent();
	}


	


}
