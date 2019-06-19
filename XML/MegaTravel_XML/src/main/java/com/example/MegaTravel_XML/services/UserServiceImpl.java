package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.repository.AddressRepository;
import com.example.MegaTravel_XML.repository.AdministratorRepository;
import com.example.MegaTravel_XML.repository.AgentRepository;
import com.example.MegaTravel_XML.repository.ClientRepository;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Agent saveAgent(Agent agent) {
		// TODO Auto-generated method stub
		return agentRepository.save(agent);
	}
	
	

}
