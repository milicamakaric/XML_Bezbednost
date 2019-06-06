package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.ReservationRepository;

public class ReservationServiceImpl implements ReservationService {
 @Autowired 
 private ReservationRepository reservationRepository;
}
