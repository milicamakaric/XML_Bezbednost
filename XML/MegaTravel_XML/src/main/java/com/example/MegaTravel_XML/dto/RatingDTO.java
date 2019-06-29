package com.example.MegaTravel_XML.dto;

public class RatingDTO {
	
	private Long id;
	private Long accommodation_id;
	private Long client_id;
	private Long reservation_id;
	private Integer rating;
	private String comment;
	private boolean allowed;
	
	public RatingDTO() {
		super();
	}

	public RatingDTO(Long id, Long accommodation_id, Long client_id, Long reservation_id, Integer rating,
			String comment, boolean allowed) {
		super();
		this.id = id;
		this.accommodation_id = accommodation_id;
		this.client_id = client_id;
		this.reservation_id = reservation_id;
		this.rating = rating;
		this.comment = comment;
		this.allowed = allowed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccommodation_id() {
		return accommodation_id;
	}

	public void setAccommodation_id(Long accommodation_id) {
		this.accommodation_id = accommodation_id;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
}
