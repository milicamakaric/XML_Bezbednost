package com.example.demo.controller;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.DeviceProvider;
import com.example.demo.model.User;
import com.example.demo.model.UserTokenState;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import org.owasp.encoder.Encode;
@RestController
@RequestMapping(value="api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService servis;
	
	@Autowired
	private RoleService roleService;
	
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

public ResponseEntity<?>  registerUser(@Valid @RequestBody User user1,BindingResult result){	
		System.out.println("Dosao u registrujKorisnika");
		User oldUser= servis.findUserByMail(Encode.forHtml(user1.getEmail()));
		if(result.hasErrors()) {
			//404
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkMail(user1.getEmail())) {
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getName())) {
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getSurname())) {
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		if(oldUser==null) {
				User newUser = new User();
				
				String newPassword= user1.getPassword();
				if(newPassword.equals("") || newPassword==null) {
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
				newUser.setPassword(hashedPass);
				newUser.setRoles(Arrays.asList(roleService.findByName("ROLE_USER")));
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
public ResponseEntity<?>  userLogin(@Valid @RequestBody User newUser, @Context HttpServletRequest request, HttpServletResponse response, Device device, BindingResult result) throws IOException{		
	System.out.println("usao u login u controlleru");	
		if(!checkMail(newUser.getEmail())) {
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		
		User postoji = servis.findUserByMail(Encode.forHtml(newUser.getEmail()));
		if(result.hasErrors()) {
			//404
		
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.NOT_FOUND);
		}
		
		if(postoji!=null) {
				
			if(org.springframework.security.crypto.bcrypt.BCrypt.checkpw(newUser.getPassword(), postoji.getPassword())){	
			System.out.println("Uspesna prijava :), email: " + postoji.getEmail());
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

@RequestMapping(
		value = "/changetocertificated",
		method = RequestMethod.POST,
		consumes = MediaType.TEXT_PLAIN_VALUE)
public void changeUserToCertificated(@RequestBody String param) 
{
		System.out.println("dosau u change user");
		Long id_issuer = Long.parseLong(param);
		User user = servis.findOneById(id_issuer);
		user.setCertificated(true);
		servis.saveUser(user);
	
}		


		
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@RequestMapping(value = "/userprofile", method = RequestMethod.POST,
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getProfile(@RequestBody String token) 
	{
		User notvalidUser = new User();
		System.out.println("IMA TOKEN: " + token);
		String email = tokenUtils.getUsernameFromToken(token);
		System.out.println("USERNAME: " + email);
		if(!checkMail(email)) {
			return  new ResponseEntity<User>(notvalidUser, HttpStatus.NOT_FOUND);
		}
		User user = (User) this.servis.findUserByMail(Encode.forHtml(email));
	   

	    //System.out.println("Korisnik: " + user.getEmail());
		return  new ResponseEntity<User>(user, HttpStatus.OK);
	}

@PreAuthorize("hasRole('ADMIN') or hasRole('USER')") //ovde mogu pristupiti svi koji su registrovani
@RequestMapping(value="/allCertificatedUsers", method = RequestMethod.GET,
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
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
public boolean checkCharacters(String data) {
	if(data.isEmpty()) {
		return false;
	}
	for(Character c :data.toCharArray()) {
		if(Character.isWhitespace(c)== false && Character.isLetterOrDigit(c) == false) {
			return false;
		}
	}
	
	return true;
}
public boolean checkId(String id) {
	if(id.isEmpty()) {
		return false;
	}
	for(Character c :id.toCharArray()) {
		if(!Character.isDigit(c)) {
			return false;
		}
	}
	return true;
}
public boolean checkMail(String mail) {
	if(mail.isEmpty()) {
		return false;
	}
	if(mail.contains(";")) {
		return false;
	}
	
	if(mail.contains(",")) {
		return false;
	}
	for(Character c:mail.toCharArray()) {
		if(Character.isWhitespace(c)) {
			return false;
		
		}
			
	}
	return true;
}
	


@PreAuthorize("hasRole('ADMIN') or hasRole('USER')") //ovde mogu pristupiti svi koji su registrovani
@RequestMapping(value="/logout", method = RequestMethod.GET,
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public void logOutUser(){	
	
	SecurityContextHolder.clearContext();
}


	@RequestMapping(value="/communication", 
	method = RequestMethod.GET)
	
	public String  communication(){		
		System.out.println("Dosao u communication");
		
		return "success";
	}


@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@RequestMapping(
		value = "/rateUs",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
public boolean rateUs(@RequestBody int stars) 
{
		System.out.println("dosao u rate us, broj zvezdica: " + stars);
		if(stars<=0 || stars>5 )
			return false;
		
		return true;
	
}		

}