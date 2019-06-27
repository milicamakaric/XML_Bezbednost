package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Reservation;


@Service
public interface ReservationService {
	List<Reservation> getAll();
	
	public Reservation save(Reservation reservation);
	void deleteAll();

	List<Reservation> getByRoomId(Long id);
	
	Reservation saveReservation(Reservation res);

	Reservation getById(Long id);
	void setStatus(Long id, String status);
}
