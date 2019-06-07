package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.CancelationRepository;

public class CancelationServiceImpl implements CancelationService {
	
	@Autowired
	private CancelationRepository cancelationRepository;

}
