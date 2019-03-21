package com.example.demo.controller;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Certificate;
import com.example.demo.model.IssuerData;
import com.example.demo.model.SubjectData;
import com.example.demo.model.User;
import com.example.demo.pki.certificates.CertificateGenerator;
import com.example.demo.service.CertificateService;
import com.example.demo.service.SoftwareService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="api/certificates")
public class CertificateController {
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private SoftwareService softwareService;
	
	@Autowired
	private UserService userService;
	
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
		Certificate saved = certificateService.saveCertificate(certificate);
		softwareService.updateCertificated(id_subject);
		java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), id_subject, id_issuer, start_date_cert, end_date_cert);
		return certificate;
	}

	private java.security.cert.Certificate createCertificateWithGen(Long id_cert, Long id_subject, Long id_issuer,
			Date start_date_cert, Date end_date_cert) {
		User subject = userService.findOneById(id_subject);
		User issuer = userService.findOneById(id_issuer);
		
		try {
			SubjectData subjectData = generateSubjectData(id_cert, subject, start_date_cert, end_date_cert);
			
			KeyPair keyPairIssuer = generateKeyPair();
			IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), issuer);
		    
			//Generise se sertifikat za subjekta, potpisan od strane issuer-a
			CertificateGenerator cg = new CertificateGenerator();
			X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
			
			//Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
			cert.verify(keyPairIssuer.getPublic());
			System.out.println("\nValidacija uspesna :)");
			
			//Ovde se desava exception, jer se validacija vrsi putem drugog kljuca
			KeyPair anotherPair = generateKeyPair();
			cert.verify(anotherPair.getPublic());
			return cert;
		} catch(CertificateException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			System.out.println("\nValidacija neuspesna :(");
			e.printStackTrace();
		}
		
		return null;
	}
	
	private IssuerData generateIssuerData(PrivateKey private1, User issuer) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    
	    builder.addRDN(BCStyle.SURNAME, issuer.getSurname());
	    builder.addRDN(BCStyle.GIVENNAME, issuer.getName());
	    builder.addRDN(BCStyle.E, issuer.getEmail());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, issuer.getId().toString());

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(private1, builder.build());
	}

	private KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}

	private SubjectData generateSubjectData(Long id_cert, User subject, Date start_date_cert,
			Date end_date_cert) {
		
			KeyPair keyPairSubject = generateKeyPair();
			
			
			//Serijski broj sertifikata
			String sn=id_cert.toString();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.SURNAME, subject.getSurname());
		    builder.addRDN(BCStyle.GIVENNAME, subject.getName());
		    builder.addRDN(BCStyle.E, subject.getEmail());
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, subject.getId().toString());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, start_date_cert, end_date_cert);
		
	}

	@RequestMapping(
			value = "/createSelfSigned/{id_issuer}/{start_date}/{end_date}",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate createSelfCertificate(@PathVariable("id_issuer") Long id_issuer, @PathVariable("start_date") String start_date,@PathVariable("end_date") String end_date) throws ParseException
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date_cert = format.parse(start_date);
		Date end_date_cert = format.parse(end_date);
		System.out.println("SELFCertificate: " + " id_issuer=" + id_issuer + " start=" + start_date_cert + " end_date=" + end_date_cert);
		String serialNumber = ""; //ovde treba preuzeti serialNumber iz onog X500...
		Certificate certificate = new Certificate(serialNumber,id_issuer,id_issuer, start_date_cert, end_date_cert, false, true, "");
		Certificate saved = certificateService.saveCertificate(certificate);
		java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), id_issuer, id_issuer, start_date_cert, end_date_cert);
		
		return certificate;
	}

	
	@RequestMapping(
			value = "/revoke/{id}/{reason}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate revokeCertificate(@PathVariable("id") Long id,@PathVariable("reason") String reason){
		System.out.println("Usao u revokeCertificate "+ id.toString());
		Certificate certificate = certificateService.findOneByIdSubject(id);
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
