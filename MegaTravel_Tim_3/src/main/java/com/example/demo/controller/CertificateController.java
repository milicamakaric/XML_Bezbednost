package com.example.demo.controller;

import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.x509.extension.X509ExtensionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CertificateDTO;
import com.example.demo.model.Certificate;
import com.example.demo.model.IssuerData;
import com.example.demo.model.Software;
import com.example.demo.model.SubjectData;
import com.example.demo.model.User;
import com.example.demo.pki.certificates.CertificateGenerator;
import com.example.demo.pki.keystore.KeyStoreReader;
import com.example.demo.pki.keystore.KeyStoreWriter;
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
	
	private KeyStoreWriter keyStoreWriter;
	
	private KeyPair keyPairIssuer;
	
	@PostConstruct
	public void init(){
		keyStoreWriter = new KeyStoreWriter();
		String globalPass = "globalPass";
		keyStoreWriter.loadKeyStore(null, globalPass.toCharArray());
		keyStoreWriter.saveKeyStore("globalKeyStore", globalPass.toCharArray());
		keyPairIssuer = generateKeyPair();
	}
	
	
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
		
		Certificate certificate = new Certificate(id_issuer,id_subject, start_date_cert, end_date_cert, false, false, "");
		Certificate saved = certificateService.saveCertificate(certificate);
		softwareService.updateCertificated(id_subject);
		
		Software subject = softwareService.findOneById(id_subject);
		User issuer = userService.findOneById(id_issuer);
		
		SubjectData subjectData = generateSubjectData(saved.getId(), subject, start_date_cert, end_date_cert);
		IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), issuer);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), subjectData, issuerData, keyPairIssuer.getPublic(), start_date_cert, end_date_cert);
		
		String certificatePass = "certificatePass" + subject.getId();
		keyStoreWriter.write(certificatePass, subjectData.getPrivateKey(), certificatePass.toCharArray(), cert);
		String globalPass = "globalPass";
		keyStoreWriter.saveKeyStore("globalKeyStore", globalPass.toCharArray());
		
		KeyStoreWriter keyStoreWriterLocal = new KeyStoreWriter();
		keyStoreWriterLocal.loadKeyStore(null, subject.getAlias().toCharArray());
		System.out.println("[CertificateController - createCertificate] subject alias: " + subject.getAlias());
		keyStoreWriterLocal.saveKeyStore("localKeyStore"+subject.getAlias(), subject.getAlias().toCharArray());
		String localAlias="myCertificate";
		
		keyStoreWriterLocal.write(localAlias, subjectData.getPrivateKey(), localAlias.toCharArray(), cert);
		keyStoreWriterLocal.saveKeyStore("localKeyStore"+subject.getAlias(), subject.getAlias().toCharArray());
		
		return certificate;
	}
/*
	private java.security.cert.Certificate createCertificateWithGen(Long id_cert, SubjectData subjectData, IssuerData issuerData,
			PublicKey publicKey, Date start_date_cert, Date end_date_cert) {
		
		try {
			
		    
			//Generise se sertifikat za subjekta, potpisan od strane issuer-a
			CertificateGenerator cg = new CertificateGenerator();
			X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
			
			//Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
			cert.verify(publicKey);
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
	*/
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
	
		Certificate certificate = new Certificate(id_issuer,id_issuer, start_date_cert, end_date_cert, false, true, "");
		Certificate saved = certificateService.saveCertificate(certificate);
		
		User subject = userService.findOneById(id_issuer);
		User issuer = userService.findOneById(id_issuer);
		
		SubjectData subjectData = generateSubjectData(saved.getId(), subject, start_date_cert, end_date_cert);
		
		IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), issuer);
		subjectData.setPublicKey(keyPairIssuer.getPublic());
		subjectData.setPrivateKey(keyPairIssuer.getPrivate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//java.security.cert.Certificate cert = createCertificateWithGen(saved.getId(), subjectData, issuerData, keyPairIssuer.getPublic(), start_date_cert, end_date_cert);
		
		String selfCertificatePass = "selfCertificatePass";
		keyStoreWriter.write("selfCertificate", keyPairIssuer.getPrivate(), selfCertificatePass.toCharArray(), cert);
		String globalPass = "globalPass";
		keyStoreWriter.saveKeyStore("globalKeyStore", globalPass.toCharArray());
		
		
		System.out.println("[CertificateController - validateCertificate PRE]: issuer pubic key: " + keyPairIssuer.getPublic());
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("[CertificateController - validateCertificate PRE]: issuer private key: " + keyPairIssuer.getPrivate());
		
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
	
	@RequestMapping(
			value = "/validate/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<String> validateCertificate(@PathVariable("id") Long id) throws Exception{
		System.out.println("Usao u validateCertificate "+ id.toString());
		Certificate certificate = certificateService.findOneByIdSubject(id);
		String message = "The certificate is valid.";
		
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
			
			KeyStoreReader keyStoreReader = new KeyStoreReader();
			String certificatePass = "certificatePass" + id;
			java.security.cert.Certificate cert = keyStoreReader.readCertificate("globalKeyStore", "globalPass", certificatePass);
			System.out.println("[CertificateController - validateCertificate]: cert - " + cert);
			
			/*
			X509Certificate  certificateX509 = (X509Certificate ) cert;
			
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			byte[] extVal = certificateX509.getExtensionValue(Extension.authorityInfoAccess.getId());
			AuthorityInformationAccess aia = AuthorityInformationAccess.getInstance(X509ExtensionUtil.fromExtensionValue(extVal));

			// check if there is a URL to issuer's certificate
			AccessDescription[] descriptions = aia.getAccessDescriptions();
			for (AccessDescription ad : descriptions) {
			    // check if it's a URL to issuer's certificate
			    if (ad.getAccessMethod().equals(X509ObjectIdentifiers.id_ad_caIssuers)) {
			        GeneralName location = ad.getAccessLocation();
			        if (location.getTagNo() == GeneralName.uniformResourceIdentifier) {
			            String issuerUrl = location.getName().toString();
			            // http URL to issuer (test in your browser to see if it's a valid certificate)
			            // you can use java.net.URL.openStream() to create a InputStream and create
			            // the certificate with your CertificateFactory
			            URL url = new URL(issuerUrl);
			            X509Certificate issuer = (X509Certificate) certificateFactory.generateCertificate(url.openStream());
			            System.out.println("[CertificateController - validateCertificate]: issuer cert - " + issuer);
			        }
			    }
			}
			/*
			  Principal subjectDN = issuer.getSubjectDN();
			  Principal issuerDN = uploaded.getIssuerDN();
			  if (!subjectDN.equals(issuerDN)) {
			      return false;
			  }
			  PublicKey pubKey = issuer.getPublicKey();
			  try {
			      uploaded.verify(pubKey);
			  } catch (Exception e) {
			      return false;
			  }
			  return true;
			 */
			
			java.security.cert.Certificate issuerCert = keyStoreReader.readCertificate("globalKeyStore", "globalPass", "selfCertificate");
			PrivateKey issuerPrivateKey = keyStoreReader.readPrivateKey("globalKeyStore", "globalPass", "selfCertificate", "selfCertificatePass");
			
			System.out.println("[CertificateController - validateCertificate]: issuer pubic key: " + issuerCert.getPublicKey());
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("[CertificateController - validateCertificate]: issuer private key: " + issuerPrivateKey);
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
				e.printStackTrace();
			}
		}
		System.out.println("[CertificateController - validateCertificate]: message: " + message);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@RequestMapping(value="/allDTO", method = RequestMethod.GET)
	public List<CertificateDTO> getAllCertificatesDTO(){		
		List<CertificateDTO> allCertificatesDTO = new ArrayList<CertificateDTO>();
		List<Software> allSoftwares= softwareService.getAll();
		
		for (Software S : allSoftwares) {
			if(S.isCertificated()) {
				Certificate certificate = certificateService.findOneByIdSubject(S.getId());
				if(certificate!=null) {
					CertificateDTO newCertificateDTO = new CertificateDTO(S.getName(), certificate.getStartDate(),certificate.getEndDate(), certificate.isRevoked(), certificate.getReasonForRevokation(), true);
					allCertificatesDTO.add(newCertificateDTO);
				}else {
					CertificateDTO newCertificateDTO = new CertificateDTO();
					newCertificateDTO.setCertified(false);
					newCertificateDTO.setSoftware(S.getName());
					allCertificatesDTO.add(newCertificateDTO);
						
				}
			}else {
				//software nema dodeljen sertifikat
				CertificateDTO newCertificateDTO = new CertificateDTO();
				newCertificateDTO.setCertified(false);
				newCertificateDTO.setSoftware(S.getName());
				allCertificatesDTO.add(newCertificateDTO);
			}
		}
		
		return  allCertificatesDTO;
	}
}
