package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Cancelation;

@Service
public interface CancelationService {

	Cancelation save(Cancelation cancelation);

}
