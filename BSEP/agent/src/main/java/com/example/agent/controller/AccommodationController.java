package com.example.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.agent.model.Accommodation;
import com.example.agent.model.Room;
import com.example.agent.services.AccommodationService;
import com.example.agent.services.RoomService;


@RestController
@RequestMapping(value="api/agent")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AccommodationController {
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private RoomService roomService;
	
	@PreAuthorize("hasAuthority('getAccommodations')")
	@RequestMapping(value="/getAccommodations/{id}", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getAccommodations(@PathVariable("id") Long agent_id){		
		System.out.println("getAccommodations entered");
		List<Accommodation> ret = accommodationService.getAgentAccommodations(agent_id);
		return new ResponseEntity<List<Accommodation>>(ret, HttpStatus.OK);
	}

	
	@PreAuthorize("hasAuthority('addRoom')")
	@RequestMapping(value = "/addRoom", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Room room) {

		System.out.println("add room entered");
		Room saved = this.roomService.saveRoom(room);
		return  new ResponseEntity<Room>(saved, HttpStatus.OK);
	}
	
}
