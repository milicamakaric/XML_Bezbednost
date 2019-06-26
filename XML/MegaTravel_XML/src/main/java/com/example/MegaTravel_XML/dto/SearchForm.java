package com.example.MegaTravel_XML.dto;

import java.util.ArrayList;
import java.util.Date;

public class SearchForm {

	protected String city;
	protected Date startDate;
	protected Date endDate;
	protected int numberOfPeople;
	protected String type;
	protected ArrayList<String> listOfServices;
	protected String cancelation;
	protected double distance;
	protected int stars;
	public SearchForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchForm(String city, Date startDate, Date endDate,
			int numberOfPeople, String type, ArrayList<String> listOfServices,
			String cancelation, double distance, int stars) {
		super();
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfPeople = numberOfPeople;
		this.type = type;
		this.listOfServices = listOfServices;
		this.cancelation = cancelation;
		this.distance = distance;
		this.stars=stars;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getListOfServices() {
		return listOfServices;
	}
	public void setListOfServices(ArrayList<String> listOfServices) {
		this.listOfServices = listOfServices;
	}
	public String getCancelation() {
		return cancelation;
	}
	public void setCancelation(String cancelation) {
		this.cancelation = cancelation;
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
