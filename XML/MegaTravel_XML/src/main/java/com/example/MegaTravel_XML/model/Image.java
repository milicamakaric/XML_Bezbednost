package com.example.MegaTravel_XML.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable{
		
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Lob
	 private byte[] data;
	 
	 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 private Accommodation accommodation;

	 public Image() {}
	 
	 public Image(Long id, byte[] data, Accommodation accommodation) {
		super();
		this.id = id;
		this.data = data;
		this.accommodation = accommodation;
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}
	 
	 

	 
}
