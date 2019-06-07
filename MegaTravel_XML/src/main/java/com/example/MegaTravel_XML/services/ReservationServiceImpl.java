package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.repository.ReservationRepository;
@Service
public class ReservationServiceImpl implements ReservationService {
 @Autowired 
 private ReservationRepository reservationRepository;
}
