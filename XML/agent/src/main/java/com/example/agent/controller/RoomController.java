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
import com.example.agent.model.SaveRoomResponse;
import com.example.agent.services.AccommodationService;
import com.example.agent.services.RoomService;
import com.example.agent.soap.UpdateClient;

@RestController
@RequestMapping(value="room")
@CrossOrigin(origins = "http://localhost:4202")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private UpdateClient updateClient;
	
	@PreAuthorize("hasAuthority('getAgentRooms')")
	@RequestMapping(value="/getRooms/{acc_id}/{ulogovan_id}", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getRoomsByAccIdAgentId(@PathVariable("acc_id") Long acc_id, @PathVariable("ulogovan_id") Long agent_id){
	
		System.out.println("Usao u room controller - get rooms");
		List<Room> allRooms = roomService.getAll();
		List<Room> ret = new ArrayList<Room>();
		
		for(int i=0;i<allRooms.size();i++){
			Room r = allRooms.get(i);
			if(r.getAccommodation()!=null && r.getAgent()!=null)
			{	
				if(r.getAccommodation().getId().equals(acc_id) && r.getAgent().getId().equals(agent_id)){
					ret.add(r);
				}
			}
		}
		
		
		return new ResponseEntity<List<Room>>(ret , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('addRoom')")
	@RequestMapping(value = "/addRoom/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Room room,@PathVariable("id") Long id) {
		Accommodation acc = accommodationService.getById(id);
		System.out.println("add room entered");
		room.setAccommodation(acc);
		
		SaveRoomResponse response = updateClient.saveRoom(room);
		System.out.println("saved in response: " + response.isSaved());
		
		if(response.isSaved()) {
			System.out.println("soba je sacuvana u glavnom beku");
			Room saved = roomService.saveRoom(response.getRoom());
			return  new ResponseEntity<Room>(saved, HttpStatus.OK);
		}else {
			System.out.println("soba nije sacuvana u glavnom beku");
			return  new ResponseEntity<Room>(room, HttpStatus.OK);
		}
	}

	
}
