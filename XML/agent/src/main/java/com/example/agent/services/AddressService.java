package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Address;

@Service
public interface AddressService {


	Address getByStreetNumberCityPTTState(String street, String number,
			String city, int ptt, String state);

	Address save(Address address);

	Address getById(Long i);

	

}
