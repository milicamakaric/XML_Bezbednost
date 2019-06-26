package com.example.agent.services;

import java.util.List;

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
		return priceRepository.save(price);
	}
	
	@Override
	public void deleteAll() {
		priceRepository.deleteAll();
	}

	@Override
	public List<PriceForNight> getAll() {
		// TODO Auto-generated method stub
		return priceRepository.findAll();
	}

 
}
