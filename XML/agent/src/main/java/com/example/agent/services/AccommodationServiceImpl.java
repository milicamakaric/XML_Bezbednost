package com.example.agent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Accommodation;
import com.example.agent.repository.AccommodationRepository;

@Service
public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepository accommodationRepository;


	public List<Accommodation> getAll() {
		
		return accommodationRepository.findAll();
	}

	@Override
	public Accommodation saveAccomodation(Accommodation accommodation) {
		return accommodationRepository.save(accommodation);
	}


	@Override
	public Accommodation getAccommodationById(Accommodation accommodation) {
		return null;
	}

	@Override
	public Accommodation getById(Long accommodation_id) {
		
		return this.accommodationRepository.findById(accommodation_id).get();
	}



	
}
