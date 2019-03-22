package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Relation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "keyOne", nullable = false)
	private Long keyOne;
	
	@Column(name = "keyTwo", nullable = false)
	private Long keyTwo;
	
	public Relation() {
		super();
	}

	public Long getKeyOne() {
		return keyOne;
	}

	public void setKeyOne(Long keyOne) {
		this.keyOne = keyOne;
	}

	public Long getKeyTwo() {
		return keyTwo;
	}

	public void setKeyTwo(Long keyTwo) {
		this.keyTwo = keyTwo;
	}
	
	public Relation(Long keyOne, Long keyTwo) {
		super();
		this.keyOne = keyOne;
		this.keyTwo = keyTwo;
	}
	
	
}
