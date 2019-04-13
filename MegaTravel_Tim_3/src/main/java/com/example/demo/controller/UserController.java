package com.example.demo.controller;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.bouncycastle.crypto.generators.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.DeviceProvider;
import com.example.demo.dto.CertificateDTO;
import com.example.demo.model.Authority;
import com.example.demo.model.Certificate;
import com.example.demo.model.Software;
import com.example.demo.model.User;
import com.example.demo.model.UserTokenState;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService servis;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DeviceProvider deviceProvider;
	
	@RequestMapping(value="/registration", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<User>  registerUser(@RequestBody User user1){		
		System.out.println("Dosao u registrujKorisnika");
		User oldUser= servis.findUserByMail(user1.getEmail());
		
		if(oldUser==null) {
				User newUser = new User();
				
				String newPassword= user1.getPassword();
				if(newPassword.equals("") || newPassword==null ) {
					return null;
				}
				String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
				
				System.out.println("===== Hesiranje lozinke =====");
				//byte[] hashedPassword = hashPassword(newPassword, salt);
				//BASE64Encoder encoder = new BASE64Encoder();
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newPassword, salt);
				newUser.setEmail(user1.getEmail());
				newUser.setName(user1.getName());
				newUser.setSurname(user1.getSurname());
				System.out.println("stara sifra "+newUser.getPassword());
				newUser.setPassword(hashedPass);
				System.out.println("nova sifra "+newUser.getPassword()); 
				List<Authority> authorities = new ArrayList<Authority>();
				Authority auth = new Authority();
				auth.setName("ROLE_USER");
				authorities.add(auth);
				newUser.setAuthorities(authorities);
				servis.saveUser(newUser);
				
				return new ResponseEntity<>(newUser, HttpStatus.OK);
		}else {
			user1.setEmail("error");
			return new ResponseEntity<>(user1, HttpStatus.OK);
		}		
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
	
	

	
@RequestMapping(value="/login", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?>  userLogin(@RequestBody User newUser, @Context HttpServletRequest request, HttpServletResponse response, Device device) throws IOException{		
	System.out.println("usao u login u controlleru");	
		User postoji = servis.findUserByMail(newUser.getEmail());
		
		if(postoji!=null) {
				
			if(org.springframework.security.crypto.bcrypt.BCrypt.checkpw(newUser.getPassword(), postoji.getPassword())){	
			System.out.println("Uspesna prijava :)");
			}else{
				return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
		
			}
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							postoji.getEmail(),
							newUser.getPassword()));

			// Ubaci username + password u kontext
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token
			User user = (User) authentication.getPrincipal();
			String jwt = tokenUtils.generateToken(user.getEmail(), device);
			int expiresIn = tokenUtils.getExpiredIn(device);

			return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
		
		}else {
		
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);

		}
			
	}

		

@RequestMapping(value = "/userprofile", method = RequestMethod.POST)
	public ResponseEntity<User> getProfile(@RequestBody String token) 
	{
	
		System.out.println("IMA TOKEN: " + token);
		String email = tokenUtils.getUsernameFromToken(token);
		
		System.out.println("USERNAME: " + email);
	    User user = (User) this.servis.findUserByMail(email);
	    
	    System.out.println("Korisnik: " + user.getEmail());
	    		
		return  new ResponseEntity<User>(user, HttpStatus.OK);
	}

@RequestMapping(value="/allCertificatedUsers", method = RequestMethod.GET)
public List<User> getAllCertificatedUsers(){	
	List<User> all=servis.getAll();
	List<User> certificated = new ArrayList<User>();
	
	for(User user : all)
	{
		if(user.isCertificated())
			certificated.add(user);
	}
	
	System.out.println("Ima certificated usera: " + certificated.size());
	if(certificated.size() > 0)
	{
		System.out.println("Ima certificated usera: " + certificated.size());
		return certificated;
	}
	else 
		return null;
	
}
}