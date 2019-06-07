package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MegaTravel_XML.repository.RoomRepository;

public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;
	
}
