package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Address;
import com.example.agent.repository.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override

	public Address getByStreetNumberCityPTTState(String street, String number,
			String city, int ptt, String state) {
		
		return addressRepository.getByStreetNumberCityPTTState(street, number, city, ptt, state);
	}

	@Override
	public Address save(Address address) {
		
		return addressRepository.save(address);
	}

	@Override
	public Address getById(Long i) {
		
		return addressRepository.findById(i).get();
	}



	


}
