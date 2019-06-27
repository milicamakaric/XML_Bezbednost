package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.PriceForNight;

@Service
public interface PriceForNightService {
	
	public List<PriceForNight> getAll(); 
	public PriceForNight save(PriceForNight price);

}
