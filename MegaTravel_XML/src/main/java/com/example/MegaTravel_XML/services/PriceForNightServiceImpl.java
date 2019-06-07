package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.repository.PriceForNightRepository;
@Service
public class PriceForNightServiceImpl implements PriceForNightService{
 @Autowired 
 private PriceForNightRepository priceRepository;
}
