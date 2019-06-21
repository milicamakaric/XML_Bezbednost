package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.repository.AccommodationRepository;

@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepository accommodationRepository;

	@Override
	public Accommodation getAccommodationById(Accommodation accommodation) {
		long id = accommodation.getId();
		return accommodationRepository.findById(id);
	}

	public List<Accommodation> getAll() {
		
		return accommodationRepository.findAll();
	}

	@Override
	public Accommodation saveAccomodation(Accommodation accommodation) {
		return accommodationRepository.save(accommodation);
	}

	@Override
	public Accommodation getById(Long accommodation_id) {
		
		return accommodationRepository.findById(accommodation_id).get();
	}



	
}
