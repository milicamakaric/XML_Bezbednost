package com.example.agent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.model.Room;
import com.example.agent.repository.RoomRepository;
@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Room> getAll() {
	
		return roomRepository.findAll();
	}

	@Override
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public void deleteAll() {
		roomRepository.deleteAll();
	}
	public Room findById(Long id) {
		// TODO Auto-generated method stub
		return roomRepository.findById(id).get();
	}
	
}
