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
import com.example.demo.pki.keystore.KeyStoreWriter;
import com.example.demo.service.CertificateService;
import com.example.demo.service.SoftwareService;

@RestController
@RequestMapping(value="api/certificates")
public class CertificateController {
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private SoftwareService softwareService;
	
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
		String serialNumber = ""; //ovde treba preuzeti serialNumber iz onog X500...
		Certificate certificate = new Certificate(serialNumber,id_issuer,id_subject, start_date_cert, end_date_cert, false, false, "");
		certificateService.saveCertificate(certificate);
		softwareService.updateCertificated(id_subject);
		return certificate;
	}

	@RequestMapping(
			value = "/createSelfSigned/{id_issuer}/{start_date}/{end_date}",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate createSelfCertificate(@PathVariable("id_issuer") Long id_issuer, @PathVariable("start_date") String start_date,@PathVariable("end_date") String end_date) throws ParseException
	{
		
		//TODO: napraviti globalni keystore
		KeyStoreWriter keyStoreWriter = new KeyStoreWriter();
		String globalPass = "globalPass";
		keyStoreWriter.loadKeyStore(null, globalPass.toCharArray());
		keyStoreWriter.saveKeyStore("globalKeyStore", globalPass.toCharArray());
		
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date_cert = format.parse(start_date);
		Date end_date_cert = format.parse(end_date);
		System.out.println("SELFCertificate: " + " id_issuer=" + id_issuer + " start=" + start_date_cert + " end_date=" + end_date_cert);
		String serialNumber = ""; //ovde treba preuzeti serialNumber iz onog X500...
		Certificate certificate = new Certificate(serialNumber,id_issuer,id_issuer, start_date_cert, end_date_cert, false, true, "");
		certificateService.saveCertificate(certificate);
		
		return certificate;
	}

	
	@RequestMapping(
			value = "/revoke/{id}/{reason}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate revokeCertificate(@PathVariable("id") Long id,@PathVariable("reason") String reason){
		System.out.println("Usao u revokeCertificate "+ id.toString());
		Certificate certificate = certificateService.findOneById(id);
		if(certificate!=null) {
			certificate.setRevoked(true);
			System.out.println("Razlog je "+reason);
			certificate.setReasonForRevokation(reason);
			certificateService.saveCertificate(certificate);
			return certificate;
		}else {
			return null;
		}
		
	}

}
