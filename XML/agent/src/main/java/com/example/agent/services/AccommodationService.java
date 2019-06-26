package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Accommodation;

@Service
public interface AccommodationService {

	Accommodation getAccommodationById(Accommodation accommodation);
	Accommodation saveAccomodation(Accommodation accommodation);
	Accommodation getById(Long accommodation_id);
	List<Accommodation> getAll();
	void deleteAll();
}
