package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Accommodation;


@Service
public interface AccommodationService {

	Accommodation saveAccomodation(Accommodation accommodation);
	Accommodation getById(long accommodation_id);
	List<Accommodation> getAgentAccommodations(long id);
}
