package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.ReservationRepository;

public class ReservationServiceImpl implements ReservationService {
 @Autowired 
 private ReservationRepository reservationRepository;
}
