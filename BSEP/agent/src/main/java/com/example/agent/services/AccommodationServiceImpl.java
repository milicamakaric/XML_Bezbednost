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
	public Accommodation getById(long accommodation_id) {
		// TODO Auto-generated method stub
		return this.accommodationRepository.findById(accommodation_id);
	}

	@Override
	public List<Accommodation> getAgentAccommodations(long id) {
		// TODO Auto-generated method stub
		return accommodationRepository.getByAgentId(id);
	}

	
	
}
