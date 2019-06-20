package com.example.MegaTravel_XML.controller;


import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.Role;
import com.example.MegaTravel_XML.model.User;
import com.example.MegaTravel_XML.model.UserTokenState;
import com.example.MegaTravel_XML.services.AddressService;
import com.example.MegaTravel_XML.services.AddressServiceImpl;
import com.example.MegaTravel_XML.services.RoleService;
import com.example.MegaTravel_XML.services.UserService;
import com.example.MegaTravel_XML.services.UserServiceImpl;

@RestController
@RequestMapping(value="api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AddressService addressService;
	
	
	@CrossOrigin(origins = "http://localhost:4201")
	@RequestMapping(value="/registration", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<?>  registerUser(@Valid @RequestBody User user1,BindingResult result){	
		System.out.println("Dosao u registrujKorisnika");
		System.out.println("USer " + user1.getEmail() + user1.getPassword() + user1.getAddress().getCity());
		Client oldUser= userService.findClientByEmail(Encode.forHtml(user1.getEmail()));
		if(result.hasErrors()) {
			//404
			
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkMail(user1.getEmail())) {
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getName())) {
			
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getSurname())) {
			
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(oldUser==null) {
				Client newUser = new Client();
				
				String newPassword= user1.getPassword();
				if(newPassword.equals("") || newPassword==null) {
					return null;
				}
				String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
				
				System.out.println("===== Hesiranje lozinke =====");
				//byte[] hashedPassword = hashPassword(newPassword, salt);
				//BASE64Encoder encoder = new BASE64Encoder();
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newPassword, salt);
				System.out.println("hashed " + hashedPass);
				newUser.setEmail(user1.getEmail());
				newUser.setName(user1.getName());
				newUser.setSurname(user1.getSurname());
				newUser.setPassword(hashedPass);
				newUser.setRoles(Arrays.asList(roleService.findByName("ROLE_CLIENT")));
				newUser.setRole("ROLE_CLIENT");
				Address address = addressService.getByStreetNumberCityPTTState(user1.getAddress().getStreet(), user1.getAddress().getNumber(), user1.getAddress().getCity(), user1.getAddress().getPtt(), user1.getAddress().getState());
				if(address==null)
				{
					System.out.println("Adresa je null");
					Address newAddress= new Address();
					newAddress.setStreet(user1.getAddress().getStreet());
					newAddress.setNumber(user1.getAddress().getNumber());
					newAddress.setCity(user1.getAddress().getCity());
					newAddress.setPtt(user1.getAddress().getPtt());
					newAddress.setState(user1.getAddress().getState());
					//dodaj longitude i latitude
					Address saved = addressService.save(newAddress);
					newUser.setAddress(saved);
				}
				else
				{
					newUser.setAddress(address);
				}
				
				//newUser.setAddress(addressService.getById((long) 1));
				newUser.setEnabled(false);
				System.out.println("Cuvanje u bazu");
				userService.saveClient(newUser);
			
				return new ResponseEntity<>(newUser, HttpStatus.OK);
		}else {
			user1.setEmail("error");
			
			return new ResponseEntity<>(user1, HttpStatus.OK);
		}	
		
		
	}
	
	public boolean checkCharacters(String data) {
		if(data.isEmpty()) {
			return false;
		}
		for(Character c :data.toCharArray()) {
			if(Character.isWhitespace(c)== false && Character.isLetterOrDigit(c) == false) {
				return false;
			}
		}
		
		return true;
	}
	public boolean checkId(String id) {
		if(id.isEmpty()) {
			return false;
		}
		for(Character c :id.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	public boolean checkMail(String mail) {
		if(mail.isEmpty()) {
			return false;
		}
		if(mail.contains(";")) {
			return false;
		}
		
		if(mail.contains(",")) {
			return false;
		}
		for(Character c:mail.toCharArray()) {
			if(Character.isWhitespace(c)) {
				return false;
			
			}
				
		}
		return true;
	}
	
	@PreAuthorize("hasAuthority('allUsers')")
	@RequestMapping(value="/getUsers", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(){		
		System.out.println("getUsers entered");
		
		List<Client> clients = this.userService.getUsers();
		
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@RequestMapping(value="/addAgent", 
			method = RequestMethod.POST)
	public ResponseEntity<?> addAgent(@RequestBody Agent agent){		
		
		System.out.println("add new agent entered"+agent.getEmail()+"a pass "+agent.getPassword());
		Agent oldUser= userService.findAgentByEmail(Encode.forHtml(agent.getEmail()));
		if(oldUser == null) {
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
			addressService.save(newAddress);

			agent.setAddress(newAddress);
		}
		agent.setRoles(Arrays.asList(roleService.findByName("ROLE_AGENT")));
		agent.setRole("ROLE_AGENT");
		
		String newPassword= agent.getPassword();
		if(newPassword.equals("") || newPassword==null) {
			return null;
		}
		String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
		
		System.out.println("===== Hesiranje lozinke =====");
		//byte[] hashedPassword = hashPassword(newPassword, salt);
		//BASE64Encoder encoder = new BASE64Encoder();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newPassword, salt);
		System.out.println("hashed " + hashedPass);
		agent.setPassword(hashedPass);
		agent.setPassChanged(false);
		Agent saved  =  userService.saveAgent(agent);
		return new ResponseEntity<Agent>(saved, HttpStatus.OK);
		}else {
			agent.setEmail("error");	
			agent.setRoles(Arrays.asList(roleService.findByName("ROLE_AGENT")));
			agent.setRole("ROLE_AGENT");
			return new ResponseEntity<>(agent, HttpStatus.OK);
	
		}
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
