package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Cancelation;
import com.example.agent.repository.CancelationRepository;
@Service
public class CancelationServiceImpl implements CancelationService {
	
	@Autowired
	private CancelationRepository cancelationRepository;

	@Override
	public Cancelation save(Cancelation cancelation) {
		return cancelationRepository.save(cancelation);
	}

	@Override
	public void deleteAll() {
		cancelationRepository.deleteAll();
	}

}
