package com.example.agent.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.agent.model.Reservation;
import com.example.agent.model.Room;
import com.example.agent.model.SaveReservationRequest;
import com.example.agent.model.SaveReservationResponse;
import com.example.agent.model.SaveRoomRequest;
import com.example.agent.model.SaveRoomResponse;

public class UpdateClient extends WebServiceGatewaySupport {
	
	public SaveRoomResponse saveRoom(Room room) {

		SaveRoomRequest request = new SaveRoomRequest();
		request.setRoom(room);
		
		System.out.println("saveRoom in UpdateClient entered");

		SaveRoomResponse response = (SaveRoomResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public SaveReservationResponse saveReservation(Reservation reservation) {

		SaveReservationRequest request = new SaveReservationRequest();
		request.setReservation(reservation);
		
		System.out.println("saveReservation in UpdateClient entered");

		SaveReservationResponse response = (SaveReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}

}
