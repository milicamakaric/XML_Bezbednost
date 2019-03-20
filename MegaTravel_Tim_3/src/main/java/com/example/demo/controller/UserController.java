package com.example.demo.controller;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Encoder;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
	private UserService servis;
	
	@RequestMapping(value="/registration", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<User>  registrujKorisnika(@RequestBody User newUser){		
		
		User oldUser= servis.findUserByMail(newUser.getEmail());
		
		if(oldUser!=null) {
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
				
				servis.saveUser(newUser);
				
				return new ResponseEntity<>(newUser, HttpStatus.OK);
		}else {
			return null;
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
		
		User user = (User) request.getSession().getAttribute("ulogovan");
		
		return user;
	}
	
}