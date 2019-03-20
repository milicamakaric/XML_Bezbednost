package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Software;
import com.example.demo.model.User;

@RestController
@RequestMapping(value="api/softwares")
public class SoftwareController {
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> registrujKorisnika(@RequestBody User newUser){		
		
		
		String newPassword= newUser.getPassword();
		if(newPassword.equals("") || newPassword==null ) {
			return null;
		}
		byte[] salt = generateSalt();
		
		System.out.println("===== Hesiranje lozinke =====");
		byte[] hashedPassword = hashPassword(newPassword, salt);
	
		return new ResponseEntity<>(newUser, HttpStatus.OK);
		
	}

}
