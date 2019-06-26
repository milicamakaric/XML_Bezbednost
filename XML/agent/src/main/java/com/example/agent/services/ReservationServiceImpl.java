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
		 
		 public List<Reservation> getAll()
		 {
			return reservationRepository.findAll();
		 }

		@Override
		public List<Reservation> getByRoomId(Long id) {
			// TODO Auto-generated method stub
			return reservationRepository.findByRoomId(id);
		}

		@Override
		public Reservation saveReservation(Reservation res) {
			// TODO Auto-generated method stub
			return reservationRepository.save(res);
		}
		 
		
		 
		 

		
}
