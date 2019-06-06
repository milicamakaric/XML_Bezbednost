package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.RoomRepository;

public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;
	
}
