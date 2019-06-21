package com.example.MegaTravel_XML.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.AccommodationType;
import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.services.AccommodationServiceImpl;
import com.example.MegaTravel_XML.services.AccommodationTypeService;
import com.example.MegaTravel_XML.services.AddressService;

@RestController
@RequestMapping(value="api/accommodation")
@CrossOrigin(origins = "http://localhost:4200")
public class AccommodationController {

	@Autowired
	private AccommodationServiceImpl accommodationService;
	
	@Autowired
	private AccommodationTypeService accommodationTypeService;
	@Autowired
	private AddressService addressService;
	
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Accommodation>> getAllAccommodations(){		
		System.out.println("get all acc");
		List<Accommodation> accommodations = accommodationService.getAll();
		return new ResponseEntity<List<Accommodation>>(accommodations, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('addAccommodationType')")
	@RequestMapping(value="/addNewAccommodationType", 
			method = RequestMethod.POST)
	public ResponseEntity<?> addNewAccommodationType(@RequestBody AccommodationType accommodationType){		
		System.out.println("addNewAccommodationType entered");
		AccommodationType postoji = accommodationTypeService.getByName(accommodationType.getName());
		
		if(postoji!=null)
		{
			System.out.println("Postoji");
			return new ResponseEntity<AccommodationType>(accommodationType, HttpStatus.NOT_FOUND);
		}
		
		AccommodationType saved = accommodationTypeService.save(accommodationType);
		
		return new ResponseEntity<AccommodationType>(saved, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('addAccommodation')")
	@RequestMapping(value="/addNewAccommodation", 
			method = RequestMethod.POST)
	public ResponseEntity<?> addNewAccommodation(@RequestBody Accommodation accommodation){		
		System.out.println("addNewAccommodation entered");
		
		Address address = addressService.getByStreetNumberCityPTTState(accommodation.getAddress().getStreet(), accommodation.getAddress().getNumber(), accommodation.getAddress().getCity(), accommodation.getAddress().getPtt(), accommodation.getAddress().getState());
		if(address==null)
		{
			System.out.println("Adresa je null");
			Address newAddress= new Address();
			newAddress.setStreet(accommodation.getAddress().getStreet());
			newAddress.setNumber(accommodation.getAddress().getNumber());
			newAddress.setCity(accommodation.getAddress().getCity());
			newAddress.setPtt(accommodation.getAddress().getPtt());
			newAddress.setState(accommodation.getAddress().getState());
			newAddress.setDistance(accommodation.getAddress().getDistance());
			Address saved = addressService.save(newAddress);
			accommodation.setAddress(saved);
		}else {
			accommodation.setAddress(address);
			
		}
		Accommodation saved =	accommodationService.saveAccomodation(accommodation);
		return new ResponseEntity<Accommodation>(saved, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('getTypes')")
	@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
	public ResponseEntity<?> getTypes() {

		System.out.println("getTypes additional service entered");
		
		List<AccommodationType> types = this.accommodationTypeService.getTypes();
	    		
		return  new ResponseEntity<List<AccommodationType>>(types, HttpStatus.OK);
	}
}
