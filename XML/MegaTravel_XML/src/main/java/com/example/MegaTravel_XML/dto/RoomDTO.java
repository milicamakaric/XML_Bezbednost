package com.example.MegaTravel_XML.dto;

import java.util.List;

public class RoomDTO {
	
	protected Long id;
	
    protected int capacity;
    
    protected double defaultPrice;
    
    protected List<PriceForNightDTO> specialPrices;
    
    public RoomDTO() {}

	public RoomDTO(Long id, int capacity, double defaultPrice, List<PriceForNightDTO> specialPrices) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.defaultPrice = defaultPrice;
		this.specialPrices = specialPrices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public List<PriceForNightDTO> getSpecialPrices() {
		return specialPrices;
	}

	public void setSpecialPrices(List<PriceForNightDTO> specialPrices) {
		this.specialPrices = specialPrices;
	}
	
	
    
}
