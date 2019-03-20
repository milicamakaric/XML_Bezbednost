package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Certificate;

@Service
public interface CertificateService {

	List<Certificate> getAll();
}
