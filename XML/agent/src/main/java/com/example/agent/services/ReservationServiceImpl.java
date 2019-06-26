package com.example.agent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Reservation;
import com.example.agent.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
		 @Autowired 
		 private ReservationRepository reservationRepository1;
		 
		 public List<Reservation> getAll()
		 {
			return reservationRepository1.findAll();
		 }

		@Override
		public List<Reservation> getByRoomId(Long id) {
			// TODO Auto-generated method stub
			return reservationRepository1.findByRoomId(id);
		}

		@Override
		public Reservation saveReservation(Reservation res) {
			// TODO Auto-generated method stub
			return reservationRepository1.save(res);
		}
		 
	 
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository1.save(reservation);
	}

	@Override
	public void deleteAll() {
		reservationRepository1.deleteAll();
	}
}
