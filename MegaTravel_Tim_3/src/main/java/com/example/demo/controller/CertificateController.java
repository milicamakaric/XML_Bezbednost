package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Certificate;
import com.example.demo.service.CertificateService;

@RestController
@RequestMapping(value="api/certificates")
public class CertificateController {
	
	@Autowired
	private CertificateService certificateService;
	
	@RequestMapping(
			value = "/create/{id_subject}/{id_issuer}/{start_date}/{end_date}",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate createCertificate(@PathVariable("id_subject") Long id_subject, @PathVariable("id_issuer") Long id_issuer, @PathVariable("start_date") String start_date,@PathVariable("end_date") String end_date) throws ParseException
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date_cert = format.parse(start_date);
		Date end_date_cert = format.parse(end_date);
		System.out.println("Certificate: id_subject=" + id_subject + " id_issuer=" + id_issuer + " start=" + start_date_cert + " end_date=" + end_date_cert);
		
		return null;
	}

}
