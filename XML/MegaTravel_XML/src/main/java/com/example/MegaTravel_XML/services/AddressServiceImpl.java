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
<<<<<<< HEAD
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

=======
	public Address findAddress(double longitude, double latitude) {
		// TODO Auto-generated method stub
		return addressRepository.findAddress(longitude, latitude);
	}

	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c
}
