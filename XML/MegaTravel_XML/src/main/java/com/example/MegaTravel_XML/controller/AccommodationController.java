package com.example.MegaTravel_XML.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.AccommodationType;
import com.example.MegaTravel_XML.model.AdditionalService;
import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.services.AccommodationServiceImpl;
import com.example.MegaTravel_XML.services.AccommodationTypeService;
import com.example.MegaTravel_XML.services.AdditionalServiceService;
import com.example.MegaTravel_XML.services.AddressService;
import com.example.MegaTravel_XML.services.CancelationService;
import com.example.MegaTravel_XML.services.UserService;

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
	
	@Autowired
	private CancelationService cancelationService;
	
	@Autowired
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	private UserService userService;
	
	
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
		System.out.println("ADDITIONAL SERVICES: " + accommodation.getAdditionalServices());
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
		
		AccommodationType tipSmestaja= accommodationTypeService.getByName(accommodation.getType().getName());
		if(tipSmestaja==null)
		{
			AccommodationType newAccType = new AccommodationType();
			newAccType.setName(accommodation.getType().getName());
			AccommodationType savedType = accommodationTypeService.save(newAccType);
			accommodation.setType(savedType);
		}
		else{
			accommodation.setType(tipSmestaja);
		}
		
		Cancelation cancelation  = new Cancelation();
		cancelation.setAllowed(accommodation.getCancelation().isAllowed());
		cancelation.setNumberOfDays(accommodation.getCancelation().getNumberOfDays());
		Cancelation savedCancel = cancelationService.save(cancelation);
		accommodation.setCancelation(savedCancel);
		List<AdditionalService> services_list = new ArrayList<AdditionalService>();
		for(int i=0;i<accommodation.getAdditionalServices().size();i++)
		{
			AdditionalService as = additionalServiceService.getById(accommodation.getAdditionalServices().get(i).getId());
			services_list.add(as);
		}
		
		accommodation.setAdditionalServices(services_list);
		Accommodation saved =	accommodationService.saveAccomodation(accommodation);
		return new ResponseEntity<Accommodation>(saved, HttpStatus.OK);
	}
	//@PreAuthorize("hasAuthority('addAgentsToAccommodation')")
	@PreAuthorize("hasAuthority('addAccommodation')")
	@RequestMapping(value="/addAgentsToAccommodation/{id}", 
			method = RequestMethod.POST)
	public Accommodation addAgentsToAccommodation(@RequestBody ArrayList<String> agents, @PathVariable("id") long idAccommodation){		
		System.out.println("entered in addAgentsToAccommodation");
		Accommodation acc = new Accommodation();
	/*
		acc= accommodationService.getById(idAccommodation);
		System.out.println("Pronasao accommodation "+ acc.getName());
		ArrayList<String> choosenAgents = agents;
		for(String id : choosenAgents) {
			Agent addedAgent = userService.findAgentByEmail(id);
		}
		*/
		return acc;
		}
	
	
	@PreAuthorize("hasAuthority('getTypes')")
	@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
	public ResponseEntity<?> getTypes() {

		System.out.println("getTypes additional service entered");
		
		List<AccommodationType> types = this.accommodationTypeService.getTypes();
	    		
		return  new ResponseEntity<List<AccommodationType>>(types, HttpStatus.OK);
	}
}
