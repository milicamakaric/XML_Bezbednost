package com.example.MegaTravel_XML.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.services.UserServiceImpl;

@RestController
@RequestMapping(value="api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@RequestMapping(value="/getUsers", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(){		
		System.out.println("getUsers entered");
		
		List<Client> clients = this.userService.getUsers();
		
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

}
