package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.RoomService;

@RestController
@RequestMapping(value="room")

public class RoomController {
	@Autowired
	private RoomService roomService;
}
