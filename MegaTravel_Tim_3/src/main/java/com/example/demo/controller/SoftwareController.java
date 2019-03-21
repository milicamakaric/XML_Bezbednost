package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Certificate;
import com.example.demo.model.Software;
import com.example.demo.service.CertificateService;
import com.example.demo.service.SoftwareService;

@RestController
@RequestMapping(value="api/softwares")
public class SoftwareController {
	
	@Autowired
	private SoftwareService softwareService;
	
	@Autowired
	private CertificateService certificateService;
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllSoftwares(){		
		
		List<Software> softwares = softwareService.getAll();
		return new ResponseEntity<List<Software>>(softwares, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCertificated", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllCertificated(){		
		//vraca softvere koji imaju povucene sertifikate
		List<Software> softwares = softwareService.getAll();
		List<Software> certificated = new ArrayList<Software>();
		List<Certificate> certificates = certificateService.getAll();
		System.out.println("getall ");
		
		for(int i=0; i<softwares.size(); i++) {
			if(softwares.get(i).isCertificated())
					for(Certificate C : certificates) {
						String idSubject= C.getIdSubject().toString();
						String idSoftware= softwares.get(i).getId().toString();

						System.out.println("IDS " +idSubject+ " idS "+idSoftware);
						
						if(C.isRevoked() && idSubject.equals(idSoftware)) {
							if(C.isCa()==false) {
								certificated.add(softwares.get(i));			
							}
							
						}
					}
		}
		
		return new ResponseEntity<List<Software>>(certificated, HttpStatus.OK);
	}
	@RequestMapping(value="/getCertificatedNR", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllCertificatedNR(){		
		//vraca softvere koji imaju  sertifikate koji nisu povuceni
		List<Software> softwares = softwareService.getAll();
		List<Software> certificated = new ArrayList<Software>();
		List<Certificate> certificates = certificateService.getAll();
		System.out.println("getallCNR ");
		for(int i=0; i<softwares.size(); i++) {
			if(softwares.get(i).isCertificated())
					for(Certificate C : certificates) {
						String idSubject= C.getIdSubject().toString();
						String idSoftware= softwares.get(i).getId().toString();
						System.out.println("IDS " +idSubject+ " idS "+idSoftware);
						if(idSubject.equals(idSoftware) && C.isRevoked()==false) {
							System.out.println("Poklapa se sertifikat i issuer");						
							if(C.isCa()==false) {
								certificated.add(softwares.get(i));			
							}}
					}
		}
		
		return new ResponseEntity<List<Software>>(certificated, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getNotCertificated", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getAllNotCertificated(){		
		
		List<Software> softwares = softwareService.getAll();
		List<Software> notCertificated = new ArrayList<Software>();
		
		for(int i=0; i<softwares.size(); i++) {
			if(!softwares.get(i).isCertificated())
		
				notCertificated.add(softwares.get(i));
		}
		return new ResponseEntity<List<Software>>(notCertificated, HttpStatus.OK);
	}

	
	@RequestMapping(value="/getSelfSigned", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean existsSelfSigned(){		
		
	
		List<Certificate> certificates = certificateService.getAll();
		for(int i=0; i<certificates.size(); i++) {
			if (certificates.get(i).isCa())
				return true;
		}
		
		return false;
	}
	
	
}
