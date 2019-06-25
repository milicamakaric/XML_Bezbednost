package com.example.MegaTravel_XML.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.example.MegaTravel_XML.dto.AccommodationDTO;
import com.example.MegaTravel_XML.dto.SearchForm;
import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.AccommodationType;
import com.example.MegaTravel_XML.model.AdditionalService;
import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.model.Comment;
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.model.Room;
import com.example.MegaTravel_XML.services.AccommodationServiceImpl;
import com.example.MegaTravel_XML.services.AccommodationTypeService;
import com.example.MegaTravel_XML.services.AdditionalServiceService;
import com.example.MegaTravel_XML.services.AddressService;
import com.example.MegaTravel_XML.services.CancelationService;
import com.example.MegaTravel_XML.services.ReservationService;
import com.example.MegaTravel_XML.services.UserService;

@RestController
@RequestMapping(value="api/accommodation")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
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
	
	@Autowired
	private ReservationService reservationService;
	
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Accommodation>> getAllAccommodations(){		
		System.out.println("get all acc");
		List<Accommodation> accommodations = accommodationService.getAll();
		return new ResponseEntity<List<Accommodation>>(accommodations, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('getComm')")
	@RequestMapping(value="/getComments/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getNotAllowedComments(@PathVariable("id") Long id){		
		System.out.println("get all comments");
		Accommodation acc = accommodationService.getById(id);
		List<Comment> comments  = acc.getComments();
		List<Comment> retcomments  = new ArrayList<Comment>();
		
		for(int i = 0;i<acc.getComments().size();i++) {
			if(!comments.get(i).isAllowed()) {
				retcomments.add(comments.get(i));
			}
		}
		return new ResponseEntity<List<Comment>>(retcomments, HttpStatus.OK);
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
		System.out.println("ADDITIONAL SERVICES: " + accommodation.getAditionalServices());
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
		for(int i=0;i<accommodation.getAditionalServices().size();i++)
		{
			AdditionalService as = additionalServiceService.getById(accommodation.getAditionalServices().get(i).getId());
			services_list.add(as);
		}
		
		accommodation.setAditionalServices(services_list);
		Accommodation saved =	accommodationService.saveAccomodation(accommodation);
		return new ResponseEntity<Accommodation>(saved, HttpStatus.OK);
	}
	//@PreAuthorize("hasAuthority('addAgentsToAccommodation')")
	//@PreAuthorize("hasAuthority('addAccommodation')")
	@RequestMapping(value="/addAgentsToAccommodation/{id}/{agents}", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addAgentsToAccommodation(@PathVariable("id") long idAccommodation,@PathVariable String agents){		
		System.out.println("entered in addAgentsToAccommodation");
		Accommodation acc = new Accommodation();
	    System.out.println(agents);
		acc= accommodationService.getById(idAccommodation);
		System.out.println("Pronasao accommodation "+ acc.getName());
		String[] listAgents = agents.split("=");
		List<Agent> agenti = acc.getAgent();
		
		for(String id : listAgents) {
			if(id!=" " || !id.equals("")) {
			System.out.println("ID AGENT " + id);
			Agent addedAgent = (Agent) userService.findById(Long.parseLong(id));
			agenti.add(addedAgent);
			}
		}
		acc.setAgent(agenti);
		this.accommodationService.saveAccomodation(acc);
		System.out.println("Saved accommodation");
		return new ResponseEntity<Accommodation>(acc, HttpStatus.OK);

		}
	
	
	//@PreAuthorize("hasAuthority('getTypes')")
	@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
	public ResponseEntity<?> getTypes() {

		System.out.println("getTypes additional service entered");
		
		List<AccommodationType> types = this.accommodationTypeService.getTypes();
	    		
		return  new ResponseEntity<List<AccommodationType>>(types, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", 
			method = RequestMethod.POST)
	public ResponseEntity<?> search(@RequestBody SearchForm searchForm){	
		System.out.println("SearchForm: " + searchForm.getCancelation() + " " + searchForm.getCity() + 
				" " +searchForm.getNumberOfPeople() + " " + searchForm.getType() +  " " + 
				searchForm.getEndDate() + " " + searchForm.getStartDate() + " " + searchForm.getListOfServices().size()
				+ " " + searchForm.getStars());
	
		if(searchForm.getCancelation()==null)
			searchForm.setCancelation("undefined");
		
		
		List<Address> address = addressService.getByCityName("%" +searchForm.getCity() + "%");
		System.out.println("Address size: " + address.size());
		List<Accommodation> acc1 = new ArrayList<Accommodation>();
		
		for (Address add: address)
		{
			List<Accommodation> ac = new ArrayList<Accommodation>();
			ac=accommodationService.getByAddressId(add.getId());
			if(ac.size()>0)
			{
				for(Accommodation a : ac)
				{
					acc1.add(a);
				}
			}
		}
		
		
		System.out.println("Accommodations pre fora: " +  acc1.size());
		for(Accommodation a : acc1)
		{
			
				for(Iterator<Room> roomIter = a.getRoom().iterator(); roomIter.hasNext();)
				{
					Room r = roomIter.next();
					List<Reservation> roomRes = reservationService.getByRoomId(r.getId());
					if(roomRes.size()>0)
					{
						for(Iterator<Reservation> iterRes = roomRes.iterator();iterRes.hasNext();)
						{
							Reservation res = iterRes.next();
							if((res.getStatus().equals("active") || res.getStatus().equals("reserved"))&& (searchForm.getStartDate().equals(res.getStartDate()) || searchForm.getStartDate().equals(res.getEndDate()) || searchForm.getEndDate().equals(res.getStartDate()) 
									|| ((res.getStartDate()).after(searchForm.getStartDate()) && (res.getStartDate()).before(searchForm.getEndDate()))
									|| (searchForm.getStartDate().after(res.getStartDate()) && searchForm.getStartDate().before(res.getEndDate()))
									|| (searchForm.getEndDate().after(res.getStartDate()) && searchForm.getEndDate().before(res.getEndDate()))))
							{
								roomIter.remove();
							}
						}
						
					}
					
					
				}
				
				for(Iterator<Room> roomIter2 = a.getRoom().iterator(); roomIter2.hasNext();)
				{
					Room room = roomIter2.next();
					if(room.getCapacity()< searchForm.getNumberOfPeople())
					{
						roomIter2.remove();
					}
				}
				
			
		}
		
		
		
		
		if(!searchForm.getCancelation().equals("undefined") || searchForm.getCancelation()!=null || !searchForm.getCancelation().equals(null))
		{
			if(searchForm.getCancelation().equals("true"))
			{
				for(Iterator<Accommodation> iterAcc1 = acc1.iterator(); iterAcc1.hasNext();)
				{
					Accommodation ac1 = iterAcc1.next();
					if(!ac1.getCancelation().isAllowed())
					{
						iterAcc1.remove();
					}
				}
			}
			else{
				for(Iterator<Accommodation> iterAcc2 = acc1.iterator(); iterAcc2.hasNext();)
				{
					Accommodation ac2 = iterAcc2.next();
					if(ac2.getCancelation().isAllowed())
					{
						iterAcc2.remove();
					}
				}
			}
		}
		
		
		if(!searchForm.getType().equals("undefined"))
		{
			for(Iterator<Accommodation> iterAcc3 = acc1.iterator(); iterAcc3.hasNext();)
			{
				Accommodation ac3 = iterAcc3.next();
				if(!ac3.getType().getName().equals(searchForm.getType()))
				{
					iterAcc3.remove();
				}
			}
		}
		
		
		if(searchForm.getDistance() != -1)
		{
			for(Iterator<Accommodation> iterAcc4 = acc1.iterator(); iterAcc4.hasNext();)
			{
				Accommodation ac4 = iterAcc4.next();
				if(ac4.getAddress().getDistance()>searchForm.getDistance())
				{
					iterAcc4.remove();
				}
			}
		}
		
		if(searchForm.getListOfServices().size()>0)
		{
			for(Iterator<Accommodation> iterAcc5 = acc1.iterator(); iterAcc5.hasNext();)
			{
				Accommodation ac5 = iterAcc5.next();
				if(ac5.getAditionalServices().size()>0)
				{
					
					
						for(int i=0;i<searchForm.getListOfServices().size();i++)
						{
							String service = searchForm.getListOfServices().get(i);
							AdditionalService adds = additionalServiceService.getByName(service);
							if(!ac5.getAditionalServices().contains(adds))
							{
								iterAcc5.remove();
							}
						}
					
				}
				else
				{
					iterAcc5.remove();
				}
			}
			
		}
		
		if(searchForm.getStars() != 0)
		{
			for(Iterator<Accommodation> iterAcc6 = acc1.iterator(); iterAcc6.hasNext();)
			{
				Accommodation ac6 = iterAcc6.next();
				if(ac6.getStars() != searchForm.getStars())
				{
					iterAcc6.remove();
				}
			}
		}
		List<AccommodationDTO> accommodations = new ArrayList<AccommodationDTO>();
		
		for(Accommodation konacno: acc1)
		{
			AccommodationDTO adto = new AccommodationDTO(konacno.getId(), konacno.getName(), konacno.getAddress().getStreet(), 
					konacno.getAddress().getNumber(), konacno.getAddress().getCity(), konacno.getAddress().getState(), 
					konacno.getType().getName(), konacno.getDescription(), konacno.getRoom());
			accommodations.add(adto);
		}
		
		System.out.println("Size of acc " + accommodations.size());
		return  new ResponseEntity<List<AccommodationDTO>>(accommodations, HttpStatus.OK);
	}

	
		
}
