package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.repository.AccommodationRepository;
@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepository accomodationRepository;

	@Override
	public Accommodation getAccommodationById(Accommodation accommodation) {
		long id = accommodation.getId();
		return accomodationRepository.findById(id);
	}



	
}
