package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Reservation;

@Service
public interface ReservationService {
	
	public Reservation save(Reservation reservation);
	void deleteAll();

}
