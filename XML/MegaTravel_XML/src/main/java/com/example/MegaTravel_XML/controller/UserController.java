package com.example.MegaTravel_XML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.services.AddressServiceImpl;
import com.example.MegaTravel_XML.services.UserServiceImpl;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired 
	private AddressServiceImpl addressService;
	@RequestMapping(value="/addAgent", 
			method = RequestMethod.POST)
	public ResponseEntity<?> addAgent(@RequestBody Agent agent){		
		System.out.println("add new agent entered");
		Address address =addressService.findAddress(agent.getAddress().getLongitude(),agent.getAddress().getLatitude());
		if(address == null) {
			Address newAddress = new Address();
			newAddress.setCity(agent.getAddress().getCity());
			newAddress.setLatitude(agent.getAddress().getLatitude());
			newAddress.setLongitude(agent.getAddress().getLongitude());
			newAddress.setNumber(agent.getAddress().getNumber());
			newAddress.setPtt(agent.getAddress().getPtt());
			newAddress.setState(agent.getAddress().getState());
			newAddress.setStreet(agent.getAddress().getStreet());
			addressService.saveAddress(newAddress);
			agent.setAddress(newAddress);
		}
		
		Agent saved  =  userService.saveAgent(agent);
		return new ResponseEntity<Agent>(saved, HttpStatus.OK);
	}
}
