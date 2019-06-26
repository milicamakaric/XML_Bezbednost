package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Accommodation;

@Service
public interface AccommodationService {

	Accommodation getAccommodationById(Accommodation accommodation);
	Accommodation saveAccomodation(Accommodation accommodation);
	Accommodation getById(long accommodation_id);
	List<Accommodation> getAll();
	
}
