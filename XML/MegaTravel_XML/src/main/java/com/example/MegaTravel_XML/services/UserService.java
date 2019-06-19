package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Client;


@Service
public interface UserService {
	
	public List<Client> getUsers();

}
