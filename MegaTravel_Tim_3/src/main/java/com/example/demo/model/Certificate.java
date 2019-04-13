package com.example.demo.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long idIssuer;
	
	@Column
	private Long idSubject;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private boolean revoked;
	
	@Column
	private boolean ca;
	
	@Column
	private String reasonForRevokation;
	
	@Column
	private Long idCertificateIssuer;

	public Certificate() {

	}
	
	

	public Certificate(Long idIssuer,
			Long idSubject, Date startDate, Date endDate, boolean revoked,
			boolean ca, String reasonForRevokation) {
		super();
	
		this.idIssuer = idIssuer;
		this.idSubject = idSubject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.revoked = revoked;
		this.ca = ca;
		this.reasonForRevokation = reasonForRevokation;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdIssuer() {
		return idIssuer;
	}

	public void setIdIssuer(Long idIssuer) {
		this.idIssuer = idIssuer;
	}

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
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

	public boolean isCa() {
		return ca;
	}

	public void setCa(boolean ca) {
		this.ca = ca;
	}

	public Long getIdCertificateIssuer() {
		return idCertificateIssuer;
	}

	public void setIdCertificateIssuer(Long idCertificateIssuer) {
		this.idCertificateIssuer = idCertificateIssuer;
	}
}

