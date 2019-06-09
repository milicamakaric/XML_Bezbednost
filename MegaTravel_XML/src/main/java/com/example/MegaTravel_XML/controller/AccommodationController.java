package com.example.MegaTravel_XML.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.services.AccommodationServiceImpl;

@RestController
@RequestMapping(value="api/accommodation")
public class AccommodationController {

	@Autowired
	private AccommodationServiceImpl accommodationService;
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Accommodation>> getAllAccommodations(){		
		System.out.println("get all acc");
		List<Accommodation> accommodations = accommodationService.getAll();
		return new ResponseEntity<List<Accommodation>>(accommodations, HttpStatus.OK);
	}
}
