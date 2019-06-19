package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.AdditionalService;

@Service
public interface AdditionalServiceService {
	
	public AdditionalService save(AdditionalService additionalService);

}
