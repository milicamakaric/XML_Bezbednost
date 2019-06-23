package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.AdditionalService;

@Service
public interface AdditionalServiceService {
	
	public AdditionalService save(AdditionalService additionalService);
	public List<AdditionalService> getServices();
	public AdditionalService getByName(String name);
	public AdditionalService getById(Long id);

}
