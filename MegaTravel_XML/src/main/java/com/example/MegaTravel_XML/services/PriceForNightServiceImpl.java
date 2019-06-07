package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.PriceForNightRepository;

public class PriceForNightServiceImpl implements PriceForNightService{
 @Autowired 
 private PriceForNightRepository priceRepository;
}
