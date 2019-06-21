package com.example.MegaTravel_XML.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
/*	@ManyToMany(targetEntity=Accommodation.class,mappedBy = "services") 
	private Set<Accommodation>  accommodations = new HashSet<Accommodation>();
*/
	public AdditionalService() {
		super();
	}

	public AdditionalService(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
/*
	public Set<Accommodation> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(Set<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}
*/
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

		
}
