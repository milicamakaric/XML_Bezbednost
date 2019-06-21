package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Accommodation;

@Service
public interface AccommodationService {

	Accommodation getAccommodationById(Accommodation accommodation);
	Accommodation saveAccomodation(Accommodation accommodation);
}
