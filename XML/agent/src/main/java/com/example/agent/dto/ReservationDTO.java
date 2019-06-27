package com.example.agent.dto;

import java.util.Date;

public class ReservationDTO {
	
	protected Long id;
	protected Date startDate;
	protected Date endDate;
	protected double totalPrice;
	protected String status;
	
	public ReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationDTO(Long id, Date startDate, Date endDate,
			double totalPrice, String status) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.status = status;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
