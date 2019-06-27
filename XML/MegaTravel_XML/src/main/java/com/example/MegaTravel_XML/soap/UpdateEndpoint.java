package com.example.MegaTravel_XML.soap;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.model.Room;
import com.example.MegaTravel_XML.model.SaveMessageRequest;
import com.example.MegaTravel_XML.model.SaveMessageResponse;
import com.example.MegaTravel_XML.model.SaveReservationRequest;
import com.example.MegaTravel_XML.model.SaveReservationResponse;
import com.example.MegaTravel_XML.model.SaveRoomRequest;
import com.example.MegaTravel_XML.model.SaveRoomResponse;
import com.example.MegaTravel_XML.services.MessageService;
import com.example.MegaTravel_XML.services.ReservationService;
import com.example.MegaTravel_XML.services.RoomService;

@Endpoint
public class UpdateEndpoint {
	
	private static final String NAMESPACE_URI = "http://megatravel.com/soap";
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MessageService messageService;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveReservationRequest")
    @ResponsePayload
    public SaveReservationResponse saveReservation(@RequestPayload SaveReservationRequest request) {
		
		System.out.println("saveReservation in UpdateEndpoint entered");
		SaveReservationResponse response = new SaveReservationResponse();
		
		System.out.println("reservation: start: " + request.getReservation().getStartDate() + "; end: " + 
				request.getReservation().getEndDate() + "; total: " + request.getReservation().getTotalPrice() + "; status: " + 
				request.getReservation().getStatus() + "; room id: " + request.getReservation().getRoom().getId() + "; agent id: " + 
				request.getReservation().getAgent().getId());
		
		List<Reservation> roomRes = reservationService.getByRoomId(request.getReservation().getRoom().getId());
		
		boolean flag = false;
		if(roomRes.size()>0){
			
			for(Iterator<Reservation> iterRes = roomRes.iterator();iterRes.hasNext();){
				
				Reservation reservation = request.getReservation();
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
						|| (reservation.getEndDate().after(res.getStartDate()) && reservation.getEndDate().before(res.getEndDate())))){
					
					flag = true;
					break;
				}
			}
		}
		
		if(!flag) {
			System.out.println("rezervacija se moze sacuvati na glavnom beku");
			Reservation saved = reservationService.save(request.getReservation());
			response.setReservation(saved);
			response.setSaved(true);
			
		}else {
			System.out.println("rezervacija se ne moze sacuvati na glavnom beku");
			response.setSaved(false);
		}
 
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveRoomRequest")
    @ResponsePayload
    public SaveRoomResponse saveRoom(@RequestPayload SaveRoomRequest request) {
		
		System.out.println("saveRoom in UpdateEndpoint entered");
		
		SaveRoomResponse response = new SaveRoomResponse();
		
		Room saved = roomService.save(request.getRoom());
		
		response.setRoom(saved);
		response.setSaved(true);
 
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveMessageRequest")
    @ResponsePayload
    public SaveMessageResponse saveMessage(@RequestPayload SaveMessageRequest request) {
		
		System.out.println("saveMessage in UpdateEndpoint entered");
		
		SaveMessageResponse response = new SaveMessageResponse();
		
		Message saved = messageService.save(request.getMessage());
		
		response.setMessage(saved);
		response.setSaved(true);
 
        return response;
    }

}
