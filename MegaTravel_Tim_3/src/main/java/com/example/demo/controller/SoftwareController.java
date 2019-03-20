package com.example.demo.controller;

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

}
