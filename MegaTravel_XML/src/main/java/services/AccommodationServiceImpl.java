package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.AccommodationRepository;

public class AccommodationServiceImpl implements AccommodationService{
	
	@Autowired
	private AccommodationRepository accomodationRepository;

}
