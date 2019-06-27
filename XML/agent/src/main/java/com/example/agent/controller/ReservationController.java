package com.example.agent.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.example.agent.dto.ReservationDTO;
import com.example.agent.model.Reservation;
import com.example.agent.model.Room;
import com.example.agent.model.SaveReservationResponse;
import com.example.agent.services.ReservationService;
import com.example.agent.services.RoomService;
import com.example.agent.soap.UpdateClient;


@RestController
@RequestMapping(value="reservation")
@CrossOrigin(origins = "http://localhost:4202")
public class ReservationController {
	
	@Autowired 
	private ReservationService reservationService;
	
	@Autowired
	private UpdateClient updateClient;
	
	@Autowired 
	private RoomService roomService;
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAuthority('addAgentReservation')")
	@RequestMapping(value = "/addAgentReservation", method = RequestMethod.POST)
	public ResponseEntity<?> addAgentReservation(@RequestBody Reservation reservation){
		reservation.getStartDate().setHours(0);
		reservation.getEndDate().setHours(0);
		reservation.setClient(null);
		
		System.out.println("reservation: start: " + reservation.getStartDate() + "; end: " + 
				reservation.getEndDate() + "; total: " + reservation.getTotalPrice() + "; status: " + 
				reservation.getStatus() + "; room id: " + reservation.getRoom().getId() + "; agent id: " + 
				reservation.getAgent().getId());
		
		List<Reservation> roomRes = reservationService.getByRoomId(reservation.getRoom().getId());
		
		boolean flag = false;
		if(roomRes.size()>0)
		{
			for(Iterator<Reservation> iterRes = roomRes.iterator();iterRes.hasNext();)
			{
				Reservation res = iterRes.next();
				System.out.println("res start: " + res.getStartDate() + "; res end: " + res.getEndDate());
				System.out.println("reservation.getStartDate().equals(res.getStartDate()) " + reservation.getStartDate().equals(res.getStartDate()));
				System.out.println("reservation.getStartDate().equals(res.getEndDate())" + reservation.getStartDate().equals(res.getEndDate()));
				System.out.println("reservation.getEndDate().equals(res.getStartDate())" + reservation.getEndDate().equals(res.getStartDate()));
				System.out.println("res.getStartDate().after(reservation.getStartDate())" + res.getStartDate().after(reservation.getStartDate()));
				System.out.println("res.getStartDate().before(reservation.getEndDate())" + res.getStartDate().before(reservation.getEndDate()));
				System.out.println("reservation.getStartDate().after(res.getStartDate())" + reservation.getStartDate().after(res.getStartDate()));
				System.out.println("reservation.getStartDate().before(res.getEndDate())" + reservation.getStartDate().before(res.getEndDate()));
				System.out.println("reservation.getEndDate().after(res.getStartDate())" + reservation.getEndDate().after(res.getStartDate()));
				System.out.println("reservation.getEndDate().before(res.getEndDate())" + reservation.getEndDate().before(res.getEndDate()));
				
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
			System.out.println("rezervacija se moze sacuvati u lokalnoj bazi");
			SaveReservationResponse response = updateClient.saveReservation(reservation);
			System.out.println("saved in response: " + response.isSaved());
			
			if(response.isSaved()) {
				System.out.println("rezervacija je sacuvana u glavnom beku");
				Reservation saved = reservationService.saveReservation(response.getReservation());
				return  new ResponseEntity<Reservation>(saved, HttpStatus.OK);
			}else {
				reservation.setStatus("taken");
				System.out.println("rezervacija se ne moze sacuvati zbog glavnog beka");
				return  new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
			}
			
		}else {
			reservation.setStatus("taken");
			System.out.println("rezervacija se ne moze sacuvati zbog lokalne baze");
			return  new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('getAgentReservations')")
	@RequestMapping(value="/getAgentReservations/{agent_id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAgentReservations(@PathVariable("agent_id") Long id){
		
		System.out.println("Rezervacije agenta " + id);
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
		
		List<ReservationDTO> ret = new ArrayList<ReservationDTO>();
		for(Reservation rr: allAgentRes)
		{
			ReservationDTO dto = new ReservationDTO(rr.getId(), rr.getStartDate(), rr.getEndDate(), rr.getTotalPrice(), rr.getStatus());
			ret.add(dto);
		}
		
		System.out.println("Vraca: " + ret.size());
		return  new ResponseEntity<List<ReservationDTO>>(ret, HttpStatus.OK);
	
	}
	
	@PreAuthorize("hasAuthority('getAgentReservations')")
	@RequestMapping(value="/canBeActive/{res_id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean canResBeActive(@PathVariable("res_id") Long id){
		
		Reservation res = reservationService.getById(id);
		
		Date now = new Date();
		boolean ret = false;
		if(res.getStartDate().equals(now) || res.getStartDate().before(now))
			ret =true;
		
		return ret;
	
	}
	
	@PreAuthorize("hasAuthority('getAgentReservations')")
	@RequestMapping(value="/canBeFinished/{res_id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean canResBeFinished(@PathVariable("res_id") Long id){
		
		Reservation res = reservationService.getById(id);
		
		Date now = new Date();
		boolean ret = false;
		if(res.getEndDate().equals(now) || res.getEndDate().before(now))
			ret =true;
		
		return ret;
	
	}


}
