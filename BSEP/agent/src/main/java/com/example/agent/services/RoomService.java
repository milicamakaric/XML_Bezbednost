package com.example.agent.services;

import org.springframework.stereotype.Service;

import com.example.agent.model.Room;

@Service
public interface RoomService {
	Room saveRoom(Room room);
}	
