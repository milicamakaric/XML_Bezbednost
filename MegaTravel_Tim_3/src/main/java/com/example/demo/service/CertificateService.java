package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Certificate;
import com.example.demo.repository.CertificateRepository;

public class CertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	List<Certificate> getAllCertificates()
	{
		return certificateRepository.findAll();
	}
}
