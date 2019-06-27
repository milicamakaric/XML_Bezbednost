package com.example.agent.services;

import java.util.List;
import java.util.NoSuchElementException;

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

	@Override
	public boolean containsId(Long id) {
		boolean ret = false;
		try {
			PriceForNight price = priceRepository.findById(id).get();
			System.out.println("ok 1");
			try {
				price.getId();
				ret = true;
				System.out.println("ok 2");
			}catch(Exception e) {
				System.out.println("null je");
				ret = false;
			}
		}catch(NoSuchElementException e) {
			System.out.println("ne postoji ova cena");
			ret = false;
		}
		return ret;
	}

 
}
