package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.PriceForNight;
import com.example.MegaTravel_XML.repository.PriceForNightRepository;

@Service
public class PriceForNightServiceImpl implements PriceForNightService{
	
	 @Autowired 
	 private PriceForNightRepository priceRepository;
	
	@Override
	public List<PriceForNight> getAll() {
		return priceRepository.findAll();
	}
}
