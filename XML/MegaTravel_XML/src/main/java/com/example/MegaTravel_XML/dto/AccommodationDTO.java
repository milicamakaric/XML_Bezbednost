package com.example.MegaTravel_XML.dto;

import java.util.List;

import com.example.MegaTravel_XML.model.Room;

public class AccommodationDTO {
	
	protected Long id;
	protected String name;
	protected String street;
	protected String number;
	protected String city;
	protected String state;
	protected String type;
	protected String description;
	protected List<RoomDTO> rooms;
	protected double distance;
	protected int stars;
	
	public AccommodationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AccommodationDTO(Long id, String name, String street, String number,
			String city, String state, String type, String description, List<RoomDTO> rooms, double distance, int stars) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
		this.type = type;
		this.description = description;
		this.rooms = rooms;
		this.distance=distance;
		this.stars = stars;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public List<RoomDTO> getRooms() {
		return rooms;
	}


	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public int getStars() {
		return stars;
	}


	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
	
	
	

}
