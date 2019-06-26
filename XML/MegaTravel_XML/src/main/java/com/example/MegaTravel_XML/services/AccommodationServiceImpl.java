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


	public List<Accommodation> getAll() {
		return accommodationRepository.findAll();
	}

	@Override
	public Accommodation saveAccomodation(Accommodation accommodation) {
		return accommodationRepository.save(accommodation);
	}


	@Override
	public Accommodation getAccommodationById(Accommodation accommodation) {
		return accommodationRepository.findById(accommodation.getId()).get();
	}

	@Override
	public Accommodation getById(long accommodation_id) {
		return this.accommodationRepository.findById(accommodation_id);
	}

	public List<Accommodation> getByAddressId(Long id) {
		// TODO Auto-generated method stub
		return accommodationRepository.getByAddressId(id);
	}



	
}
