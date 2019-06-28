package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired 
	private ReservationRepository reservationRepository;

	@Override
	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}
	

	@Override
	public List<Reservation> getByRoomId(Long id) {
		
		return reservationRepository.findByRoomId(id);
	}


	@Override
	public List<Reservation> getByClientId(Long id) {
		return reservationRepository.findByClientId(id);
	}
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}


	@Override
<<<<<<< HEAD
	public Reservation getById(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findById(id).get();
=======
	public void setStatus(Long id, String status) {
		reservationRepository.setStatus(id, status);
		
>>>>>>> 563ec833750e80aef2f57222835fcbddd60ba1ac
	}
}
