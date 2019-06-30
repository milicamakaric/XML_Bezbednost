package com.example.MegaTravel_XML.dto;

public class CommentDTO {
	
	private Long id;
	private String content;
	private boolean allowed;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String content, boolean allowed) {
		super();
		this.id = id;
		this.content = content;
		this.allowed = allowed;
	}

	public CommentDTO(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}

}
