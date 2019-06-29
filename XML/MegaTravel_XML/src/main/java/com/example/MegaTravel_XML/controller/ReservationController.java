package com.example.MegaTravel_XML.controller;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.PriceForNight;
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.model.Room;
import com.example.MegaTravel_XML.services.ReservationServiceImpl;
import com.example.MegaTravel_XML.services.RoomServiceImpl;
import com.example.MegaTravel_XML.services.UserServiceImpl;

@RestController
@RequestMapping(value="api/reservation")
@CrossOrigin(origins = "http://localhost:4201")
public class ReservationController {
	
 @Autowired
 private ReservationServiceImpl reservationService;
 
 @Autowired
 private UserServiceImpl userService;
 @Autowired
 private RoomServiceImpl roomService;
 
 @RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Reservation>> getAllReservations(){		
		System.out.println("get all res");
		List<Reservation> reservations = reservationService.getAll();
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}
 
 @RequestMapping(value="/getUserReservation/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reservation>> getUserReservation(@PathVariable Long id){		
		System.out.println("get user res");
		List<Reservation> reservations = reservationService.getByClientId(id);	
	
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}
 
 @RequestMapping(value="/checkCancelation/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cancelation> checkCancelation(@PathVariable Long id){		
		System.out.println("get user res");
		Reservation res = reservationService.getById(id);
		Cancelation cancel = res.getRoom().getAccommodation().getCancelation();
		if(!cancel.isAllowed()) {
			return new ResponseEntity<Cancelation>(cancel, HttpStatus.OK);
		}
		Date currentDate = new Date();
		int days = daysBetween(res.getStartDate(),currentDate);
		System.out.println("+++++++++++++++++++++++++++++++++++      "+currentDate.toString());

	
		
		System.out.println("usao ovdje"+days);
		
		if(days <= cancel.getNumberOfDays()) {
			System.out.println("prvi if");
			
			return new ResponseEntity<Cancelation>(cancel, HttpStatus.OK);	
		}else {
			System.out.println("Drugiif");
			
			cancel.setAllowed(false);
			return new ResponseEntity<Cancelation>(cancel, HttpStatus.OK);	
			
		}
		
	}
 
 public int daysBetween(Date d1, Date d2){
     return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
 }
 	
 	
 	@RequestMapping(value = "/cancelReservation", method = RequestMethod.POST)
	public ResponseEntity<?> cancelReservation(@RequestBody Reservation res) {
		System.out.println("usao da otkaze rez");
		res.setStatus("canceled");
		Reservation saved = reservationService.save(res);
		return new ResponseEntity<Reservation>(res, HttpStatus.OK);

	}
 	@PreAuthorize("hasAuthority('reserve')")
 	@RequestMapping(value = "/reserve/{id}/{idClient}", method = RequestMethod.POST)
	public ResponseEntity<?> reserve(@RequestBody Reservation res,@PathVariable Long id,@PathVariable Long idClient) {
		Room room = roomService.getById(id);
		res.setRoom(room);
		System.out.println("usao da rezervise "+room.getId());
		Client cl = userService.findClientById(idClient);
		System.out.println("+++++++++++++++++++++++++++++++");

		System.out.println("+++++++++++++++++++++++++++++++");
		List<PriceForNight> prices = room.getPrices();
		double total = 0;
		Date date = res.getStartDate();
		Date date2= res.getEndDate();
		System.out.println(" pocetak DATE "+date+"  kraj "+date2);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date2); 
		c.add(Calendar.DATE, 1);
		Date endDate = c.getTime();
		System.out.println(" END DATE "+endDate);
		while(poredi(date,endDate)) {
			
			boolean specialFound = false;
			
			for(PriceForNight price : prices) {
				if( date.after(price.getStartDate()) && date.before(price.getEndDate())) {
					
					total += price.getPrice();
					specialFound = true;
						System.out.println("usao u if "+total);
					break;
				}
					
		}
			if(!specialFound) {
				total += room.getDefaultPrice();
				
			}
			System.out.println("   cijena trenutna "+total);
			c.setTime(date); 
			c.add(Calendar.DATE, 1);
			date =c.getTime();
			System.out.println(" new DATE "+date);
		}
		res.setStatus("active");
		res.setTotalPrice(total);
		System.out.println(" id od agenta je "+room.getAgent().getId());
		Agent agent  = userService.findById(room.getAgent().getId());
		res.setAgent(agent);
		res.setClient(cl);
		Reservation saved = reservationService.save(res);
		return new ResponseEntity<Reservation>(res, HttpStatus.OK);

	}
 	
 	public boolean poredi(Date date1, Date date2) {
		if(date1.getYear()==date2.getYear() && date1.getMonth()==date2.getMonth() && date1.getDate()==date2.getDate()) {
			System.out.println("Isti su");
			return false;
		}else {
			return true;
		}
		}
}
