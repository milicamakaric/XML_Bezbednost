package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.repository.AdministratorRepository;
import com.example.MegaTravel_XML.repository.AgentRepository;
import com.example.MegaTravel_XML.repository.ClientRepository;
import com.example.MegaTravel_XML.repository.UserRepository;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.User;
import com.example.MegaTravel_XML.repository.AddressRepository;




@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdministratorRepository administratorRepository;
	

	@Autowired
	private AddressRepository addressRepository;



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
		// TODO Auto-generated method stub
		return agentRepository.save(agent);
	}

	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.save(client);
	}


	@Override
	public Client findClientById(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id).get();
	}


	@Override
	public Agent findAgentByEmail(String email) {
		// TODO Auto-generated method stub
		return agentRepository.findByEmail(email);
	}


	@Override
	public List<Agent> getAllAgents() {
		// TODO Auto-generated method stub
		return agentRepository.findAll();
	}


	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}


	


}
