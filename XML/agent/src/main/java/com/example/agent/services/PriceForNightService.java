package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.PriceForNight;

@Service
public interface PriceForNightService {
	
	PriceForNight savePriceForNight(PriceForNight price);
	void deleteAll();

}
