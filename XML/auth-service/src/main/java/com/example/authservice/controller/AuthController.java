package com.example.authservice.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.authservice.model.User;
import com.example.authservice.model.UserTokenState;
import com.example.authservice.security.TokenUtils;
import com.example.authservice.security.auth.JwtAuthenticationRequest;
import com.example.authservice.service.UserService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201", "http://localhost:4202"})
public class AuthController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest httpRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

		System.out.println("login entered in AuthController");
		
        if(!checkMail(authenticationRequest.getUsername())) {
        	System.out.println("Nije dobar email");
            // logger.logError("ULOG_UNAME_ERR. Username: " + authenticationRequest.getUsername());
            return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);
        }
        System.out.println(authenticationRequest.getUsername());

       
 
       User user = userService.getUserByEmail(authenticationRequest.getUsername());
        if(user!=null) {
        	
        	System.out.println("Prosledjena pass: " + authenticationRequest.getPassword());
			System.out.println("Hasovana pass: " + user.getPassword());
			if(org.springframework.security.crypto.bcrypt.BCrypt.checkpw(authenticationRequest.getPassword(), user.getPassword())){	
			System.out.println("Uspesna prijava :), email: " + user.getEmail());
			}else{
				return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
		
			}
			
			if(!user.isEnabled())
			{
				return new ResponseEntity<>(new UserTokenState("notActivated", 0), HttpStatus.OK);

			}
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
       HttpEntity<JwtAuthenticationRequest> HReq=new HttpEntity<JwtAuthenticationRequest>(authenticationRequest,headers);
        if(user.getRole().equals("ROLE_AGENT"))
        {
        	System.out.println("Agent se loguje");
        	 ResponseEntity<?> res1 = restTemplate.postForEntity("http://agent/agentSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);

        }else
        {
        	System.out.println("Admin ili klijent se loguje");
        	 ResponseEntity<?> res2 = restTemplate.postForEntity("http://MegaTravel-XML/api/mainSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);
             
        }
      
			
			 final Authentication authentication = manager
		                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));


		        SecurityContextHolder.getContext().setAuthentication(authentication);

		        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
		        		  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		        		  
		        		  for (GrantedAuthority authority : authorities) {
		        		    System.out.println("Authority: " + authority.getAuthority());
		        		  }
		        		
		        User user1 = (User) authentication.getPrincipal();
				String jwt = tokenUtils.generateToken(user1.getEmail(), device);
				int expiresIn = tokenUtils.getExpiredIn(device);
				
				return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        }else
        {
        	System.out.println("User je null");
        	return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);

        }
       
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
	@PreAuthorize("hasAuthority('loginAgent') or hasAuthority('loginAdmin') or hasAuthority('loginClient')")
	@RequestMapping(value="/logout", method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
			public void logOutUser(@Context HttpServletRequest request){
		
				System.out.println("Logout u auth-service");
				String token = tokenUtils.getToken(request);
				String email = tokenUtils.getUsernameFromToken(token);
				User user = (User) this.userService.getUserByEmail(email);
				
				 
				if(user.getRole().equals("ROLE_AGENT"))
		        {
		        	System.out.println("Agent bio ulogovan");
		        	 restTemplate.getForEntity("http://agent/agentSecurity/logout", void.class);

		        }else
		        {
		        	System.out.println("Admin ili klijent bio ulogovan");
		        	restTemplate.getForEntity("http://MegaTravel-XML/api/mainSecurity/logout", void.class);
		             
		        }
			
				SecurityContextHolder.clearContext();
			}
	


}
