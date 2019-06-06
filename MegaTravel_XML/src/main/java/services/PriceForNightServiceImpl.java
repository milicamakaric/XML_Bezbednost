package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.PriceForNightRepository;

public class PriceForNightServiceImpl implements PriceForNightService{
 @Autowired 
 private PriceForNightRepository priceRepository;
}
