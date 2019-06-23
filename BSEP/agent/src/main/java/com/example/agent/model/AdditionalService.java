package com.example.agent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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
