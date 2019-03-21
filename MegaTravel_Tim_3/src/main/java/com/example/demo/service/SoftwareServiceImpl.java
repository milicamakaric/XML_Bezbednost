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

}
