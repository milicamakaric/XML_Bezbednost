package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.CancelationRepository;

public class CancelationServiceImpl implements CancelationService {
	
	@Autowired
	private CancelationRepository cancelationRepository;

}
