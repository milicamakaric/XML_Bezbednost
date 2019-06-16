package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Software;
import com.example.demo.model.User;

@Service
public interface SoftwareService {
	
	List<Software> getAll();
	void updateCertificated(Long id_soft);
	Software findOneById(Long id);
	
}
