package com.example.agent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.AdditionalService;
import com.example.agent.repository.AdditionalServiceRepository;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {
	
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	@Override
	public AdditionalService save(AdditionalService additionalService) {
		return additionalServiceRepository.save(additionalService);
	}

	@Override
	public List<AdditionalService> getServices() {
		return additionalServiceRepository.findAll();
	}

	@Override
	public AdditionalService getByName(String name) {
		return additionalServiceRepository.findByName(name);
	}

	@Override
	public AdditionalService getById(Long id) {
		
		return additionalServiceRepository.findById(id).get();
	}
	

}
