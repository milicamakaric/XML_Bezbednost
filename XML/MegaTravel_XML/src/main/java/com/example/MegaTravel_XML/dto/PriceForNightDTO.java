package com.example.MegaTravel_XML.dto;

import java.util.Date;

public class PriceForNightDTO {
	
	protected Long id;
	
	protected Date startDate;
	
	protected Date endDate;
	
	protected double price;

	public PriceForNightDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceForNightDTO(Long id, Date startDate, Date endDate, double price) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
