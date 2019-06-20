package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.AccommodationType;
import com.example.MegaTravel_XML.repository.AccommodationTypeRepository;

@Service
public class AccommodationTypeServiceImpl implements AccommodationTypeService{
	
	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;

	@Override
	public AccommodationType save(AccommodationType accommodationType) {
		return accommodationTypeRepository.save(accommodationType);
	}

	@Override
	public List<AccommodationType> getTypes() {
		return accommodationTypeRepository.findAll();
	}

	@Override
	public AccommodationType getByName(String name) {
		return accommodationTypeRepository.findByName(name);
	}

}
