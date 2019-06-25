package com.example.agent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.PriceForNight;

@Service
public interface PriceForNightService {
	PriceForNight savePriceForNight(PriceForNight price);
	List<PriceForNight> getAll();
}
