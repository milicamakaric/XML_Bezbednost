package com.example.MegaTravel_XML.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Room;


@Service
public interface RoomService {
	
	public List<Room> getAll();
	public Room getById(Long id);
	public List<Room> getByAccommodationId(Long id);
	public Room save(Room room);

}
