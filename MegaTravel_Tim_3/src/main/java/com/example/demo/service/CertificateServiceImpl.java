package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Certificate;
import com.example.demo.repository.CertificateRepository;

@Service
public class CertificateServiceImpl implements CertificateService{

	@Autowired
	CertificateRepository certificateRepository;
	
	
	@Override
	public List<Certificate> getAll() {
		
		return certificateRepository.findAll();
	}

}
