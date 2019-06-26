package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.AccommodationType;

@Service
public interface AccommodationTypeService {
	
	public AccommodationType save(AccommodationType accommodationType);
	public List<AccommodationType> getTypes();
	public AccommodationType getByName(String name);
	public void deleteAll();

}
