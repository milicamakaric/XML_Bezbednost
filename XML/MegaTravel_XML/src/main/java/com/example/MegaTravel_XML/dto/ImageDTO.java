package com.example.MegaTravel_XML.dto;

public class ImageDTO {
	 private Long id;
	 private byte[] data;
	 
	 public ImageDTO() {}
	 
	 
	 public ImageDTO(Long id, byte[] data) {
		super();
		this.id = id;
		this.data = data;
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
		
	 
}
