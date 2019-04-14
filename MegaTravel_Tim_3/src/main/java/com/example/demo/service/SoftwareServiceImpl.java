package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Software;
import com.example.demo.repository.SoftwareRepository;

@Service
public class SoftwareServiceImpl implements SoftwareService {
	
	@Autowired
	SoftwareRepository repository;

	@Override
	public List<Software> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void updateCertificated(Long id_soft) {
		Software software =  repository.findById(id_soft).get();
		software.setCertificated(true);
		repository.save(software);
		
		
	}

	@Override
	public Software findOneById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}
	
	public boolean checkData(String data) {
		if(data.isEmpty()) {
			return false;
		}
		for(char C : data.toCharArray()) {
			if(!(Character.isLetterOrDigit(C) || Character.isWhitespace(C))) {
					return false;
			}
		}
		return true;
	}
	
	public boolean checkId(Long Id) {
		String data = Id.toString();
		
		for(char C : data.toCharArray()) {
			if(!Character.isDigit(C)) {
				return false;
			}
		}
		return true;
	}

}
