package com.example.agent.controller;

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

import com.example.agent.model.Client;
import com.example.agent.model.Reservation;
import com.example.agent.model.Room;
import com.example.agent.services.ReservationService;
import com.example.agent.services.RoomService;

@RestController
@RequestMapping(value="reservation")
@CrossOrigin(origins = "http://localhost:4202")
public class ReservationController {
	@Autowired
	RoomService roomService;
	@Autowired 
	ReservationService reservationService;
	
	@PreAuthorize("hasAuthority('addAgentReservation')")
	@RequestMapping(value = "/addAgentReservation", method = RequestMethod.POST)
	public ResponseEntity<?> addAgentReservation(@RequestBody Reservation reservation){
		
		List<Reservation> roomRes = reservationService.getByRoomId(reservation.getRoom().getId());
		Client cl  = new Client();
		Long id = (long) 100;
		cl.setId(id);
		reservation.setClient(cl);
		reservation.setStatus("reserved");
		boolean flag = false;
		if(roomRes.size()>0)
		{
			for(Iterator<Reservation> iterRes = roomRes.iterator();iterRes.hasNext();)
			{
				Reservation res = iterRes.next();
				if((res.getStatus().equals("active") || res.getStatus().equals("reserved"))&& (reservation.getStartDate().equals(res.getStartDate()) || reservation.getStartDate().equals(res.getEndDate()) || reservation.getEndDate().equals(res.getStartDate()) 
						|| ((res.getStartDate()).after(reservation.getStartDate()) && (res.getStartDate()).before(reservation.getEndDate()))
						|| (reservation.getStartDate().after(res.getStartDate()) && reservation.getStartDate().before(res.getEndDate()))
						|| (reservation.getEndDate().after(res.getStartDate()) && reservation.getEndDate().before(res.getEndDate()))))
				{
					flag = true;
					break;
				}
			}
			
		}
		if(!flag) {
			
			Reservation saved = reservationService.saveReservation(reservation);
			return  new ResponseEntity<Reservation>(saved, HttpStatus.OK);
			
		}else {
			reservation.setStatus("taken");
			return  new ResponseEntity<Reservation>(reservation, HttpStatus.OK);

		}
		}
	
	@PreAuthorize("hasAuthority('getAgentReservations')")
	@RequestMapping(value="/getAgentReservations/{agent_id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAgentReservations(@PathVariable("agent_id") Long id){
		
		List<Room> agentRooms = roomService.findByAgentId(id);
		List<Reservation> allAgentRes = new ArrayList<Reservation>();
		
		for(Room r: agentRooms)
		{
			List<Reservation> roomRes = reservationService.getByRoomId(r.getId());
			if(roomRes.size()>0)
			{
				for(Reservation res : roomRes)
				{
					if(!res.getStatus().equals("canceled") && res.getClient()!=null)
						allAgentRes.add(res);
				}
			}
		
		}
		
		return  new ResponseEntity<List<Reservation>>(allAgentRes, HttpStatus.OK);
	
	}


}
