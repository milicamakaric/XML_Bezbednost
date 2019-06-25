package com.example.MegaTravel_XML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Room;
import com.example.MegaTravel_XML.services.RoomServiceImpl;

@RestController
@RequestMapping(value="api/room")
@CrossOrigin(origins = "http://localhost:4201")
public class RoomController {
	@Autowired
	private RoomServiceImpl roomService;
	
	@PreAuthorize("hasAuthority('getAgentOfRoom')")
	@RequestMapping(value="/getAgentOfRoom/{room_id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAgentOfRoom(@PathVariable("room_id") Long room_id){		
		System.out.println("get agent");
		Room room = roomService.getById(room_id);
		Agent agent = room.getAgent();
		return new ResponseEntity<Agent>(agent, HttpStatus.OK);
	}
}
