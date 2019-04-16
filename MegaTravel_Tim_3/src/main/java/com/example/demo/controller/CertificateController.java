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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StringDTO;
import com.example.demo.model.Certificate;
import com.example.demo.model.IssuerData;
import com.example.demo.model.Software;
import com.example.demo.model.SubjectData;
import com.example.demo.model.User;
import com.example.demo.model.UserTokenState;
import com.example.demo.pki.certificates.CertificateGenerator;
import com.example.demo.pki.keystore.KeyStoreReader;
import com.example.demo.pki.keystore.KeyStoreWriter;
import com.example.demo.service.CertificateService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="api/certificates")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificateController {
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private UserService userService;
	
	private KeyStoreWriter keyStoreWriter;
	
	private KeyPair keyPairIssuer;
	
	@PostConstruct
	public void init(){
		keyStoreWriter = new KeyStoreWriter();
		String globalPass = "certificatePass1";
		keyStoreWriter.loadKeyStore("globalKeyStore.p12", globalPass.toCharArray());
		keyStoreWriter.saveKeyStore("globalKeyStore.p12", globalPass.toCharArray());
		keyPairIssuer = generateKeyPair();
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(
			value = "/create/{id_subject}/{start_date}/{end_date}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Certificate> createCertificate(@RequestBody String idIssuer,@PathVariable("id_subject") Long id_subject, @PathVariable("start_date") String start_date,@PathVariable("end_date") String end_date) throws ParseException
	{
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date_cert = format.parse(start_date);
		Date end_date_cert = format.parse(end_date);
		Long id_issuer = Long.parseLong(idIssuer);
		
		List<Certificate> allCertificates = certificateService.getAll();
		for(Certificate c : allCertificates)
		{
			if(c.getIdIssuer()==id_issuer && c.getIdSubject()==id_subject && c.isRevoked())
			{
				certificateService.removeCertificate(c.getId());
			}
		}
		
		System.out.println("Certificate: id_subject=" + id_subject + " id_issuer=" + id_issuer + " start=" + start_date_cert + " end_date=" + end_date_cert);
		Certificate certificate = new Certificate(id_issuer,id_subject, start_date_cert, end_date_cert, false, false, "");

		//u certificate pre cuvanja dodati idIssuerCertificate
		if(!checkId(id_issuer) || !checkId(id_subject)) {
			//403
			return new ResponseEntity<>(certificate, HttpStatus.FORBIDDEN);
		}
		Certificate issuerCertificate = certificateService.findOneByIdSubject(id_issuer);
		Long idIssuerCertificate = issuerCertificate.getId();
		certificate.setIdCertificateIssuer(idIssuerCertificate);
		
		Certificate saved = certificateService.saveCertificate(certificate);
		//softwareService.updateCertificated(id_subject);
		
		// ne radimo vise sa softverima vec sa userima
		//Software subject = softwareService.findOneById(id_subject);
		User subject = userService.findOneById(id_subject);
		User issuer = userService.findOneById(id_issuer);
		
		SubjectData subjectData = generateSubjectData(saved.getId(), subject, start_date_cert, end_date_cert);
		
		KeyStoreReader keyStoreReader = new KeyStoreReader();
		String issuerPass = "certificatePass" + issuer.getId();
		PrivateKey privateKeyIssuer = keyStoreReader.readPrivateKey("globalKeyStore.p12", "certificatePass1", issuerPass, issuerPass);
		IssuerData issuerData = generateIssuerData(privateKeyIssuer, issuer);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), subjectData, issuerData, keyPairIssuer.getPublic(), start_date_cert, end_date_cert);
		
		String certificatePass = "certificatePass" + subject.getId();
		System.out.println("certificatePass: " + certificatePass);
		keyStoreWriter.write(certificatePass, subjectData.getPrivateKey(), certificatePass.toCharArray(), cert);
		String globalPass = "certificatePass1";
		keyStoreWriter.saveKeyStore("globalKeyStore.p12", globalPass.toCharArray());
		
		KeyStoreWriter keyStoreWriterLocal = new KeyStoreWriter();
		keyStoreWriterLocal.loadKeyStore(null, subject.getId().toString().toCharArray());
		
		keyStoreWriterLocal.saveKeyStore("localKeyStore"+subject.getId()+".p12", subject.getId().toString().toCharArray());
		String localAlias="myCertificate";
		
		keyStoreWriterLocal.write(localAlias, subjectData.getPrivateKey(), localAlias.toCharArray(), cert);
		keyStoreWriterLocal.saveKeyStore("localKeyStore"+subject.getId().toString()+".p12", subject.getId().toString().toCharArray());
		
		return new ResponseEntity<Certificate>(certificate , HttpStatus.OK);
	}
	
	private IssuerData generateIssuerData(PrivateKey private1, User issuer) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    
	    builder.addRDN(BCStyle.SURNAME, issuer.getSurname());
	    builder.addRDN(BCStyle.GIVENNAME, issuer.getName());
	    builder.addRDN(BCStyle.E, issuer.getEmail());
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

	private SubjectData generateSubjectData(Long id_cert, Object subject, Date start_date_cert,
			Date end_date_cert) {
		
			KeyPair keyPairSubject = generateKeyPair();
			
			//Serijski broj sertifikata
			String sn=id_cert.toString();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
			if(subject instanceof User)
			{
				User user = (User) subject;
			    builder.addRDN(BCStyle.SURNAME, user.getSurname());
			    builder.addRDN(BCStyle.GIVENNAME, user.getName());
			    builder.addRDN(BCStyle.E, user.getEmail());
			    //UID (USER ID) je ID korisnika
			    builder.addRDN(BCStyle.UID, user.getId().toString());
			}
			else
			{
				Software soft = (Software) subject;
				builder.addRDN(BCStyle.GIVENNAME, soft.getName());
				builder.addRDN(BCStyle.UID, soft.getId().toString());
			}
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), sn, start_date_cert, end_date_cert);
		
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(
			value = "/createSelfSigned/{startDate}/{endDate}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Certificate> createSelfCertificate(@RequestBody String id_issuer_string, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		System.out.println("SELFCertificate: " + " id_issuer=" + id_issuer_string + " start=" + startDate + " end_date=" + endDate);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date_cert = format.parse(startDate);
		Date end_date_cert = format.parse(endDate);
		System.out.println("SELFCertificate: " + " id_issuer=" + id_issuer_string + " start=" + start_date_cert + " end_date=" + end_date_cert);
		
		Long id_issuer = Long.parseLong(id_issuer_string);
	
		Certificate certificate = new Certificate(id_issuer,id_issuer, start_date_cert, end_date_cert, false, true, "");
		Certificate saved = certificateService.saveCertificate(certificate);
		//u certificate pre cuvanja dodati idIssuerCertificate
		if(!checkId(id_issuer)) {
			//403
			return new ResponseEntity<>(certificate, HttpStatus.FORBIDDEN);
		}
		User subject = userService.findOneById(id_issuer);
		User issuer = userService.findOneById(id_issuer);
		
		
		SubjectData subjectData = generateSubjectData(saved.getId(), subject, start_date_cert, end_date_cert);
		
		IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), issuer);
		subjectData.setPublicKey(keyPairIssuer.getPublic());
		subjectData.setPrivateKey(keyPairIssuer.getPrivate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), subjectData, issuerData, keyPairIssuer.getPublic(), start_date_cert, end_date_cert);
		
		//String selfCertificatePass = "selfCertificatePass";
		String certificatePass = "certificatePass" + subject.getId();
		System.out.println("certificatePass: " + certificatePass);
		keyStoreWriter.write(certificatePass, keyPairIssuer.getPrivate(), certificatePass.toCharArray(), cert);
		String globalPass = "certificatePass1";
		keyStoreWriter.saveKeyStore("globalKeyStore.p12", globalPass.toCharArray());
		
		issuer.setCertificated(true);
		userService.saveUser(issuer);
		
		
		System.out.println("[CertificateController - validateCertificate PRE]: issuer pubic key: " + keyPairIssuer.getPublic());
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("[CertificateController - validateCertificate PRE]: issuer private key: " + keyPairIssuer.getPrivate());
		
		return new ResponseEntity<>(certificate, HttpStatus.OK);
	}

	
	@RequestMapping(
			value = "/revoke/{id}/{reason}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Certificate revokeCertificate(@PathVariable("id") Long id,@PathVariable("reason") String reason){
		System.out.println("Usao u revokeCertificate "+ id.toString());
		boolean valid = checkId(id);
		if(valid) {

			Certificate certificate = certificateService.findOneByIdSubject(id);
			if(certificate!=null) {
				certificate.setRevoked(true);
				System.out.println("Razlog je "+reason);
				certificate.setReasonForRevokation(reason);
				certificateService.saveCertificate(certificate);
				User issuer = userService.findOneById(id);
				issuer.setCertificated(false);
				userService.saveUser(issuer);
				List<Certificate> allCertificates = certificateService.getAll();
				for(Certificate c : allCertificates)
				{
					System.out.println("Id issuer " + c.getIdIssuer());
					if(c.getIdIssuer()==id)
					{
						System.out.println("Postoji cert");
						c.setRevoked(true);
						c.setReasonForRevokation("Issuer's certificate has been revoked.");
						certificateService.saveCertificate(c);
						User subject = userService.findOneById(c.getIdSubject());
						subject.setCertificated(false);
						userService.saveUser(subject);
					}
				}
				return certificate;
			}else {
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(
			value = "/validate/{id}",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public StringDTO validateCertificate(@PathVariable("id") Long id) throws Exception{
		System.out.println("Usao u validateCertificate "+ id.toString());
		String message = "The certificate is valid.";
		
		StringDTO stringDTO = new StringDTO();

		boolean valid = checkId(id);
		if(valid) {
			Certificate certificate = certificateService.findOneByIdSubject(id);
		
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			
			Date today_date = today.getTime();
			
			if(certificate.isRevoked()) {
				message = "The certificate has been revoked.";
				System.out.println("[CertificateController - validateCertificate]: the certificate has been revoked.");
			}else if(today_date.after(certificate.getEndDate())) {
				message = "The certificate has expired.";
				System.out.println("[CertificateController - validateCertificate]: the certificate has expired.");
			}else {
				
				while(!certificate.isCa()) {
					Long idCertificateIssuer = certificate.getIdCertificateIssuer();
					Certificate issuerCertificate = certificateService.findOneById(idCertificateIssuer);
					Long idIssuer = issuerCertificate.getIdSubject();
					System.out.println("idCertificateIssuer: " + idCertificateIssuer + "; idIssuer: " + idIssuer);
					
					Long ID = certificate.getIdSubject();
					
					KeyStoreReader keyStoreReader = new KeyStoreReader();
					String certificatePass = "certificatePass" + ID;
					java.security.cert.Certificate cert = keyStoreReader.readCertificate("globalKeyStore.p12", "certificatePass1", certificatePass);
					System.out.println("[CertificateController - validateCertificate]: cert - " + cert);
					
					String certificatePassIssuer = "certificatePass" + idIssuer;
					java.security.cert.Certificate issuerCert = keyStoreReader.readCertificate("globalKeyStore.p12", "certificatePass1", certificatePassIssuer);
					System.out.println("[CertificateController - validateCertificate]: issuerCert - " + issuerCert);
					
					try {
						cert.verify(issuerCert.getPublicKey());
					}catch(CertificateException e) {
						e.printStackTrace();
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (NoSuchProviderException e) {
						e.printStackTrace();
					} catch (SignatureException e) {
						System.out.println("[CertificateController - validateCertificate] validacija neuspesna");
						message = "The certificate is not valid.";
						//e.printStackTrace();
					}
					System.out.println("izasao iz try-catch bloka");
					certificate = issuerCertificate;
				}
			}
			
			System.out.println("[CertificateController - validateCertificate]: message: " + message);
			Map<String, String> result = new HashMap<>();
			result.put("message", message);
		
			//return ResponseEntity.accepted().body(result);
			//return new ResponseEntity<String>(message, HttpStatus.OK);
			stringDTO.setMessage(message);
			return stringDTO;
			
		}else {
			// sql injection
			message = "The certificate is not valid.";
			Map<String, String> result = new HashMap<>();
			result.put("message", message);
		
			//return ResponseEntity.accepted().body(result);
			//return new ResponseEntity<String>(message, HttpStatus.OK);
			stringDTO.setMessage(message);
			return stringDTO;
					
		}
		}
	
	@RequestMapping(
			value = "/revocationMessage/{id}",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> revocationMessage(@PathVariable("id") Long id) throws Exception{
		System.out.println("Usao u revocationMessage "+ id.toString());
		Certificate certificate = certificateService.findOneByIdSubject(id);
		String message = certificate.getReasonForRevokation();

		System.out.println("[CertificateController - revocationMessage]: message: " + message);
		
		Map<String, String> result = new HashMap<>();
		result.put("message", message);
	
		return ResponseEntity.accepted().body(result);
		
		//return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/allUsersWithCertificates", method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllCertificatesDTO(){		
		List<User> allUsers = new ArrayList<User>();
		List<Certificate> allCertificates= certificateService.getAll();
		
		for(Certificate c : allCertificates)
		{
			if(c.isCa()==false)
			{
				User u = userService.findOneById(c.getIdSubject());
				
					allUsers.add(u);
			}
			
		}
		
		if(allUsers.size()>0)
			return  allUsers;
		else
			return null;
	}
	

	public boolean checkData(String data) {
		if(data.isEmpty()) {
			return false;
		}
		for(char C : data.toCharArray()) {
			if(!(Character.isLetterOrDigit(C) || Character.isWhitespace(C))) {
					return false;
			}
		}
		return true;
	}
	
	public boolean checkId(Long Id) {
		String data = Id.toString();
		
		for(char C : data.toCharArray()) {
			if(!Character.isDigit(C)) {
				return false;
			}
		}
		return true;
	}
	
	@PreAuthorize("hasRole('USER')") 
	@RequestMapping(value="/allCertificatesIssuer/{id}", method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllCertificatesDTOWithIssuer(@PathVariable("id") String id){		
		List<User> allUsers = new ArrayList<User>();
		List<Certificate> allCertificates= certificateService.getAll();
		Long id_issuer = Long.parseLong(id);
		for(Certificate c : allCertificates)
		{
			if(c.getIdIssuer() == id_issuer)
			{
				User u = userService.findOneById(c.getIdSubject());
				
					allUsers.add(u);
				
			}
			
		}
		
		if(allUsers.size()>0)
			return  allUsers;
		else
			return null;
	}
	

}
