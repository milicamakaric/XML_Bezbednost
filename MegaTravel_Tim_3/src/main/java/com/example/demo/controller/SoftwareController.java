package com.example.demo.controller;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Certificate;
import com.example.demo.model.Relation;
import com.example.demo.model.Software;
import com.example.demo.pki.keystore.KeyStoreReader;
import com.example.demo.pki.keystore.KeyStoreWriter;
import com.example.demo.service.CertificateService;
import com.example.demo.service.RelationService;
import com.example.demo.service.SoftwareService;

@RestController
@RequestMapping(value="api/softwares")
@CrossOrigin(origins = "http://localhost:4200")
public class SoftwareController {
	
	@Autowired
	private SoftwareService softwareService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private RelationService relationService;
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Software>> getAllSoftwares(){		
		System.out.println("get all softwares");
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
	
	@RequestMapping(value="/getNotConnected/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Software>> getNotConnected(@PathVariable Long id){		
		//vraca softvere sa kojima nije ostavrena komunikacija
		List<Software> softwares = softwareService.getAll();
		List<Software> notConnected = new ArrayList<Software>();
		List<Certificate> certificates = certificateService.getAll();
		List<Relation> relations = relationService.getAll();
		
		Software chosenSoftware = softwareService.findOneById(id);
		
		for(int i=0; i<softwares.size(); i++) {
			if(softwares.get(i).getId().toString().equals(chosenSoftware.getId().toString())== false) {
			if(softwares.get(i).isCertificated()) {
					boolean found = false;
					if(relations.size() != 0) {
					for(Relation relation:relations) {
						if( ( relation.getKeyOne().toString().equals(softwares.get(i).getId().toString()) && relation.getKeyTwo().toString().equals(chosenSoftware.getId().toString())) || (relation.getKeyTwo().toString().equals(softwares.get(i).getId().toString()) && relation.getKeyOne().toString().equals(chosenSoftware.getId().toString())) ) {
							found = true;
						}
					}
					}
					if(!found) {
						for(Certificate C : certificates) {
							String idSubject= C.getIdSubject().toString();
							String idSoftware= softwares.get(i).getId().toString();
							System.out.println("IDS " +idSubject+ " idS "+idSoftware);
							
							if(C.isRevoked()== false && idSubject.equals(idSoftware)) {
								if(C.isCa()==false) {
										notConnected.add(softwares.get(i));			
									
								}
							}
							
						}
					}
				}
			}
		}
		System.out.println("Dosao u notConneected "+notConnected.size());
		return new ResponseEntity<List<Software>>(notConnected, HttpStatus.OK);
	}
	
	@RequestMapping(value="/confirmCommunication/{id}/idSoftware/{idSoftware}", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void confirmCommunication(@PathVariable("id") Long id,@PathVariable("idSoftware") Long idSoftware){		
		//formira komunikaciju izmedju dva softvera
			System.out.println("Cuvanje komunikacije izmedju dva serfitikata");
			Software firstSoftware = softwareService.findOneById(id);
			Software secondSoftware = softwareService.findOneById(idSoftware);
			KeyStoreWriter keyStoreWriter1 = new KeyStoreWriter();
			KeyStoreWriter keyStoreWriter2 = new KeyStoreWriter();
			KeyStoreReader keyStoreReader1 = new KeyStoreReader();
			KeyStoreReader keyStoreReader2 = new KeyStoreReader();
			
			String localAlias="myCertificate";
			
			PrivateKey privateKeyFirst = keyStoreReader1.readPrivateKey("localKeyStore"+firstSoftware.getAlias(), firstSoftware.getAlias(), localAlias, localAlias);
			PrivateKey privateKeySecond = keyStoreReader2.readPrivateKey("localKeyStore"+secondSoftware.getAlias(), secondSoftware.getAlias(), localAlias, localAlias);
			
			System.out.println("[SoftwareController - confirmCommunication] privateKeyFirst: " + privateKeyFirst);
			System.out.println("[SoftwareController - confirmCommunication] privateKeySecond: " + privateKeySecond);
			
			java.security.cert.Certificate firstCertificate = keyStoreReader1.readCertificate("localKeyStore"+firstSoftware.getAlias(), firstSoftware.getAlias(), localAlias);
			java.security.cert.Certificate secondCertificate = keyStoreReader2.readCertificate("localKeyStore"+secondSoftware.getAlias(), secondSoftware.getAlias(), localAlias);
			
			Relation relation = new Relation(id,idSoftware);
			relationService.saveRelation(relation);
			keyStoreWriter1.loadKeyStore("localKeyStore"+firstSoftware.getAlias(), firstSoftware.getAlias().toCharArray());
			keyStoreWriter2.loadKeyStore("localKeyStore"+secondSoftware.getAlias(), secondSoftware.getAlias().toCharArray());
			
			keyStoreWriter1.write("localKeyStore"+secondSoftware.getAlias(), privateKeySecond, secondSoftware.getAlias().toCharArray(), secondCertificate);
			keyStoreWriter1.saveKeyStore("localKeyStore"+firstSoftware.getAlias(), firstSoftware.getAlias().toCharArray());
			
			keyStoreWriter2.write("localKeyStore"+firstSoftware.getAlias(), privateKeyFirst, firstSoftware.getAlias().toCharArray(), firstCertificate);
			keyStoreWriter2.saveKeyStore("localKeyStore"+secondSoftware.getAlias(), secondSoftware.getAlias().toCharArray());
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
