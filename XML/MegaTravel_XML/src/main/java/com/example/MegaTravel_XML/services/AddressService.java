package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Address;

@Service
public interface AddressService {
	
  public Address findAddress(double longitude,double latitude);
  public Address saveAddress(Address address);
}
