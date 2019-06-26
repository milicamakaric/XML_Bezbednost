package com.example.MegaTravel_XML.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Cancelation;

@Service
public interface CancelationService {

	public Cancelation save(Cancelation cancelation);
	public List<Cancelation> getAll();

}
