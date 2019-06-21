package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.repository.CancelationRepository;
@Service
public class CancelationServiceImpl implements CancelationService {
	
	@Autowired
	private CancelationRepository cancelationRepository;

	@Override
	public Cancelation save(Cancelation cancelation) {
		return cancelationRepository.save(cancelation);
	}

}
