package com.example.MegaTravel_XML.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.services.AddressServiceImpl;
import com.example.MegaTravel_XML.services.UserServiceImpl;

@RestController
@RequestMapping(value="api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	

	@Autowired 
	private AddressServiceImpl addressService;
	
	@RequestMapping(value="/getUsers", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(){		
		System.out.println("getUsers entered");
		
		List<Client> clients = this.userService.getUsers();
		
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/addAgent", 
			method = RequestMethod.POST)
	public ResponseEntity<?> addAgent(@RequestBody Agent agent){		
		
		System.out.println("add new agent entered"+agent.getAddress().getLongitude()+" a latit "+agent.getAddress().getLatitude());
		
		Address address =addressService.findAddress(agent.getAddress().getLongitude(),agent.getAddress().getLatitude());
		if(address == null) {
			System.out.println("null ?>");
			Address newAddress = new Address();
			newAddress.setCity(agent.getAddress().getCity());
			newAddress.setLatitude(agent.getAddress().getLatitude());
			newAddress.setLongitude(agent.getAddress().getLongitude());
			newAddress.setNumber(agent.getAddress().getNumber());
			newAddress.setPtt(agent.getAddress().getPtt());
			newAddress.setState(agent.getAddress().getState());
			newAddress.setStreet(agent.getAddress().getStreet());
			//addressService.saveAddress(newAddress);
			agent.setAddress(newAddress);
		}
		
		Agent saved  =  userService.saveAgent(agent);
		return new ResponseEntity<Agent>(saved, HttpStatus.OK);
	}
	
	@RequestMapping(value="/activateUser", 
			method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@RequestBody Long id){		
		System.out.println("activiteUser entered");
		Client client = userService.findClientById(id);
		client.setEnabled(true);
		client =  userService.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value="/blockUser", 
			method = RequestMethod.PUT)
	public ResponseEntity<?> blockUser(@RequestBody Long id){		
		System.out.println("activiteUser entered");
		Client client = userService.findClientById(id);
		client.setBlocked(true);
		client =  userService.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteUser", 
			method = RequestMethod.PUT)
	public ResponseEntity<?> deleteUser(@RequestBody Long id){		
		System.out.println("activiteUser entered");
		Client client = userService.findClientById(id);
		client.setDeleted(true);
		client =  userService.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
}
