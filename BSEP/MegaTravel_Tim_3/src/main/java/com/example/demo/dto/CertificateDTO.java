package com.example.demo.dto;

import java.util.Date;

import javax.persistence.Column;

public class CertificateDTO {

	private String software;
	private Date startDate;
	private Date endDate;
	private boolean revoked;
	private String reasonForRevokation;
	private boolean certified;
	
	
	
	public CertificateDTO() {
		super();
	}



	public CertificateDTO(String software, Date startDate, Date endDate, boolean revoked, String reasonForRevokation,
			boolean certified) {
		super();
		this.software = software;
		this.startDate = startDate;
		this.endDate = endDate;
		this.revoked = revoked;
		this.reasonForRevokation = reasonForRevokation;
		this.certified = certified;
	}



	public boolean isCertified() {
		return certified;
	}



	public void setCertified(boolean certified) {
		this.certified = certified;
	}



	public String getSoftware() {
		return software;
	}



	public void setSoftware(String software) {
		this.software = software;
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



	public boolean isRevoked() {
		return revoked;
	}



	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}



	public String getReasonForRevokation() {
		return reasonForRevokation;
	}



	public void setReasonForRevokation(String reasonForRevokation) {
		this.reasonForRevokation = reasonForRevokation;
	}


}
