package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Software;
import com.example.demo.service.SoftwareService;

@RestController
@RequestMapping(value="api/softwares")
public class SoftwareController {
	
	@Autowired
	private SoftwareService softwareService;
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllSoftwares(){		
		
		List<Software> softwares = softwareService.getAll();
		return new ResponseEntity<List<Software>>(softwares, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCertificated", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllCertificated(){		
		
		List<Software> softwares = softwareService.getAll();
		List<Software> certificated = new ArrayList<Software>();
		
		for(int i=0; i<softwares.size(); i++) {
			if(softwares.get(i).isCertificated())
				certificated.add(softwares.get(i));
		}
		
		return new ResponseEntity<List<Software>>(certificated, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getNotCertificated", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllNotCertificated(){		
		
		List<Software> softwares = softwareService.getAll();
		List<Software> notCertificated = new ArrayList<Software>();
		
		for(int i=0; i<softwares.size(); i++) {
			if(!softwares.get(i).isCertificated())
				notCertificated.add(softwares.get(i));
		}
		return new ResponseEntity<List<Software>>(notCertificated, HttpStatus.OK);
	}

}
