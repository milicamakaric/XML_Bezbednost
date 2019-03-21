package com.example.demo.controller;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService servis;
	
	@RequestMapping(value="/registration", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<User>  registrujKorisnika(@RequestBody User newUser){		
		System.out.println("Dosao u registrujKorisnika");
		User oldUser= servis.findUserByMail(newUser.getEmail());
		
		if(oldUser==null) {
				String newPassword= newUser.getPassword();
				if(newPassword.equals("") || newPassword==null ) {
					return null;
				}
				byte[] salt = generateSalt();
				
				System.out.println("===== Hesiranje lozinke =====");
				byte[] hashedPassword = hashPassword(newPassword, salt);
				BASE64Encoder encoder = new BASE64Encoder();
			
				System.out.println("stara sifra "+newUser.getPassword());
				newUser.setPassword(encoder.encode(hashedPassword));
				System.out.println("nova sifra "+newUser.getPassword()); 
				newUser.setSalt(salt);
				servis.saveUser(newUser);
				
				return new ResponseEntity<>(newUser, HttpStatus.OK);
		}else {
			newUser.setEmail("error");
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}		
}
	private byte[] generateSalt() {
		//TODO: Implementirati generator salt-a prateci najbolje prakse.
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[32];
		random.nextBytes(salt);
		
		return salt;
	}
	
	
	private byte[] hashPassword(String password, byte[] salt) {
		int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        
		try {
				SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	            PBEKeySpec spec = new PBEKeySpec( passwordChars, salt, iterations, keyLength );
	            SecretKey key;
				try {
					key = skf.generateSecret( spec );
					byte[] dataHash = key.getEncoded( );
			        return dataHash;
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
		} catch (NoSuchAlgorithmException e) {
			  throw new RuntimeException( e );
		}
		return null;
	}
	
	@RequestMapping(value="/user", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(@Context HttpServletRequest request){		
		
		User user = (User) request.getSession().getAttribute("logged");
		
		return user;
	}
	

	
@RequestMapping(value="/login", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<User>  userLogin(@RequestParam String mail,@RequestParam String password ,@Context HttpServletRequest request) throws IOException{		
		User user = servis.findUserByMail(mail);
		BASE64Decoder decoder = new BASE64Decoder();
		if(user!=null) {
			if(authenticate(password,decoder.decodeBuffer(user.getPassword()),user.getSalt())){
				System.out.println("Uspesna prijava :)");
			}else{
				user.setEmail("error");
				return new ResponseEntity<>(user, HttpStatus.OK);
		
			}
			request.getSession().setAttribute("logged", user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		
		}else {
			User userReturn = new User();
			 userReturn.setEmail("error");
			return new ResponseEntity<>( userReturn, HttpStatus.OK);

		}
			
	}

		
private boolean authenticate(String attemptedPassword, byte[] storedPassword, byte[] salt) {
		//TODO: Proveriti da li je unesena lozinka (koja je u otvorenom tekstu) ista onoj koja je "uskladistena" (koja je zasticena hash & salt mehanizmom)
		byte[] newDataHash = hashPassword(attemptedPassword,salt);
		return Arrays.equals(storedPassword, newDataHash);
}	

}