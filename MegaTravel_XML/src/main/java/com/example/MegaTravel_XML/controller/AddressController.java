package com.example.MegaTravel_XML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaTravel_XML.services.AddressService;

@RestController
@RequestMapping(value="address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;

}
