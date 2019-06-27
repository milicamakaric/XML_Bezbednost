package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.PriceForNight;

@Service
public interface PriceForNightService {
	
	PriceForNight savePriceForNight(PriceForNight price);
	void deleteAll();
	List<PriceForNight> getAll();
	boolean containsId(Long id);
}
