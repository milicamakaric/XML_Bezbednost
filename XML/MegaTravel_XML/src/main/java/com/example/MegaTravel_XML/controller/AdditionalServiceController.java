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

import com.example.MegaTravel_XML.model.AdditionalService;
import com.example.MegaTravel_XML.services.AdditionalServiceService;

@RestController
@RequestMapping(value="api/additionalServices")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AdditionalServiceController {
	
	@Autowired
	private AdditionalServiceService additionalServiceService;
	
	@PreAuthorize("hasAuthority('addAdditionalService')")
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public ResponseEntity<?> addNew(@RequestBody AdditionalService additionalService) {

		System.out.println("addNew additional service entered");
		
		AdditionalService saved = this.additionalServiceService.save(additionalService);
	    		
		return  new ResponseEntity<AdditionalService>(saved, HttpStatus.OK);
	}
	
	///@PreAuthorize("hasAuthority('getServices')")
	@RequestMapping(value = "/getServices", method = RequestMethod.GET)
	public ResponseEntity<?> getServices() {

		System.out.println("getServices additional service entered");
		
		List<AdditionalService> services = this.additionalServiceService.getServices();
	    		
		return  new ResponseEntity<List<AdditionalService>>(services, HttpStatus.OK);
	}

}
