package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.AccommodationType;

@Service
public interface AccommodationTypeService {
	
	public AccommodationType save(AccommodationType accommodationType);

}
