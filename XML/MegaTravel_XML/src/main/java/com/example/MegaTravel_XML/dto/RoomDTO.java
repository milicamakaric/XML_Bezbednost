package com.example.MegaTravel_XML.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.PriceForNight;

public class RoomDTO {
	
	protected Long id;
	
    protected int capacity;
    
    protected double defaultPrice;
    
    public RoomDTO() {}

	public RoomDTO(Long id, int capacity, double defaultPrice) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.defaultPrice = defaultPrice;
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
    
}
