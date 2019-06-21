package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.AdditionalService;

@Service
public interface AdditionalServiceService {
	
	public AdditionalService save(AdditionalService additionalService);
	public List<AdditionalService> getServices();
	public AdditionalService getByName(String name);

}
