package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Cancelation;

@Service
public interface CancelationService {

	public Cancelation save(Cancelation cancelation);
	public void deleteAll();
	
}
