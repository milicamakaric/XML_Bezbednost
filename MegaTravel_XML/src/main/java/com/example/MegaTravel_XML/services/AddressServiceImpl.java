package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.AddressRepository;

public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

}
