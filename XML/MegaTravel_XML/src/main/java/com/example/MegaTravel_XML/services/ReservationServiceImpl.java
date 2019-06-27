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
<<<<<<< HEAD
	public List<Reservation> getByClientId(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findByClientId(id);
=======
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
>>>>>>> 12286e5389689826d12c54154329b041b15749bb
	}
}
