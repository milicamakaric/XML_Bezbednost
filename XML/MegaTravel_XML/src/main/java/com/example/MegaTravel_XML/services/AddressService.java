package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Address;

@Service
public interface AddressService {


	Address getByStreetNumberCityPTTState(String street, String number,
			String city, int ptt, String state);

	Address save(Address address);

	Address getById(Long i);

	

}
