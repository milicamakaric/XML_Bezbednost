package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Address;
import com.example.MegaTravel_XML.repository.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findAddress(double longitude, double latitude) {
		// TODO Auto-generated method stub
		return addressRepository.findAddress(longitude, latitude);
	}

	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

}
