package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.repository.RoomRepository;
@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;
	
}
