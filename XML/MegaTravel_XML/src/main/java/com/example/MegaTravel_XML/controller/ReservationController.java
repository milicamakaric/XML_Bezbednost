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
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.services.ReservationServiceImpl;

@RestController
@RequestMapping(value="api/reservation")
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
}
