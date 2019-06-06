package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.AddressRepository;

public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

}
