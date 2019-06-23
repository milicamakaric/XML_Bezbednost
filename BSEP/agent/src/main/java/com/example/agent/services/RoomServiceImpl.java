package com.example.agent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Room;
import com.example.agent.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room saveRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}
	
	
}
