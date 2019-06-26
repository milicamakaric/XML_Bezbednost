package com.example.agent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Reservation;
import com.example.agent.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
 @Autowired 
 private ReservationRepository reservationRepository;

	public List<Reservation> getAll() {
		
		return reservationRepository.findAll();
	}
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public void deleteAll() {
		reservationRepository.deleteAll();
	}
}
