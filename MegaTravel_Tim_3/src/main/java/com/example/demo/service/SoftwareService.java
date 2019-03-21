package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Software;

@Service
public interface SoftwareService {
	
	List<Software> getAll();
	
}
