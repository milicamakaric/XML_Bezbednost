package com.example.agent.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agent.model.Room;

@Service
public interface RoomService {

	List<Room> getAll();
	Room saveRoom(Room room);
	Room findById(Long id);
}
