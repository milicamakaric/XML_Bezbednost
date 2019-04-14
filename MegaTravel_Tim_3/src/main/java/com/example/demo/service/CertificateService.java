package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Certificate;

@Service
public interface CertificateService {
	Certificate findOneById(Long id);
	Certificate saveCertificate(Certificate certificate);
	void removeCertificate(Long id);
	Certificate findOneByIdSubject(Long id);
	Certificate findOneByIdIssuer(Long id);
	List<Certificate> getAll();
	
}
