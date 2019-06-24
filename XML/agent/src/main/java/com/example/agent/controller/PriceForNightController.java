package com.example.agent.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.model.PriceForNight;
import com.example.agent.services.PriceForNightService;

@RestController
@RequestMapping(value="price")
@CrossOrigin(origins = "http://localhost:4202")
public class PriceForNightController {
	
	@Autowired
	private PriceForNightService priceService;
	
	@PreAuthorize("hasAuthority('addSpecialPrice')")
	@RequestMapping(value = "/addSpecialPrice/{id}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSpecialPrice(@PathVariable("id") Long room_id, @RequestBody PriceForNight sp) {
		System.out.println("Id " + room_id + " price " + sp.getPrice());
		System.out.println("oooooooooooooo");
		System.out.println("add price entered"+sp.getEndDate()+sp.getStartDate());
		PriceForNight saved = this.priceService.savePriceForNight(sp);
		return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
		
	}
}
