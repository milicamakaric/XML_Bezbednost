package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.AccommodationRepository;

public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepository accomodationRepository;

}
