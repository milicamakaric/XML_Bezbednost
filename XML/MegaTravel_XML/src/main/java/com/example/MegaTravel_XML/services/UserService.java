package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.User;

@Service
public interface UserService {
	
	public Client findClientByEmail(String forHtml);

	public void saveClient(Client newUser);

	public User findByEmail(String forHtml);

}
