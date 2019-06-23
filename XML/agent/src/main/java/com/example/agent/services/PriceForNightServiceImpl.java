package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.PriceForNight;
import com.example.agent.repository.PriceForNightRepository;
@Service
public class PriceForNightServiceImpl implements PriceForNightService{
 @Autowired 
 private PriceForNightRepository priceRepository;

@Override
public PriceForNight savePriceForNight(PriceForNight price) {
	// TODO Auto-generated method stub
	return priceRepository.save(price);
}
 
 
}
