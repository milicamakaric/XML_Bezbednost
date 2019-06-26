package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Reservation;

@Service
public interface ReservationService {
	

	List<Reservation> getAll();

	List<Reservation> getByRoomId(Long id);
	

}
