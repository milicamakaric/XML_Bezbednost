package com.example.MegaTravel_XML.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;


import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.services.ReservationServiceImpl;

@RestController
@RequestMapping(value="api/reservation")
@CrossOrigin(origins = "http://localhost:4201")
public class ReservationController {
	
 @Autowired
 private ReservationServiceImpl reservationService;
 
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
}
