package com.example.agent.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.model.GetAccommodationResponse;
import com.example.agent.model.GetAccommodationTypeResponse;
import com.example.agent.model.GetAdditionalServiceResponse;
import com.example.agent.model.GetAgentResponse;
import com.example.agent.model.GetCancelationResponse;
import com.example.agent.model.GetClientResponse;
import com.example.agent.model.GetMessageResponse;
import com.example.agent.model.GetPermissionResponse;
import com.example.agent.model.GetPriceResponse;
import com.example.agent.model.GetReservationResponse;
import com.example.agent.model.GetRoleResponse;
import com.example.agent.model.GetRoomResponse;
import com.example.agent.model.User;
import com.example.agent.security.CustomUserDetailsService;
import com.example.agent.security.TokenUtils;
import com.example.agent.security.auth.JwtAuthenticationRequest;
import com.example.agent.services.AccommodationService;
import com.example.agent.services.AccommodationTypeService;
import com.example.agent.services.AdditionalServiceService;
import com.example.agent.services.CancelationService;
import com.example.agent.services.MessageService;
import com.example.agent.services.PermissionService;
import com.example.agent.services.PriceForNightService;
import com.example.agent.services.ReservationService;
import com.example.agent.services.RoleService;
import com.example.agent.services.RoomService;
import com.example.agent.services.UserService;
import com.example.agent.soap.BaseClient;

@RestController
@RequestMapping(value="agentSecurity")
@CrossOrigin(origins = "http://localhost:4202")
public class SecurityController {
	
	@Autowired
    private AuthenticationManager manager;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private BaseClient baseClient;

	@Autowired
	private AccommodationTypeService accommodationTypeService;
	
	@Autowired
	private AccommodationService accommodationService;
	
	@Autowired
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CancelationService cancelationService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private PriceForNightService priceForNightService;
	
	@Autowired
	private ReservationService reservationService;
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/setAuthentication", method = RequestMethod.POST)
    public ResponseEntity<?> setAuth(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

		System.out.println("Set authentication u agent security");
	    final Authentication authentication = manager
	            .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
	    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  
		for (GrantedAuthority authority : authorities) {
			System.out.println("Authority: " + authority.getAuthority());
		}
		
		User user = userService.findAgentByEmail(authenticationRequest.getUsername());
	    System.out.println("agent id: " + user.getId());
	    Long agent_id = user.getId();

	    deleteFromTables();
	    
	    getPermission(agent_id);
	    getRole(agent_id);
	    getClient(agent_id);
	    //getAgent(agent_id);
	    getAccommodationTypes(agent_id);
	    getAdditionalService(agent_id);
	    getCancelation(agent_id);
	    getAccommodation(agent_id);
	    getMessages(agent_id);
	    getPrice(agent_id);
	    getRoom(agent_id);
	    getReservation(agent_id);
		
		return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('loginAgent')")
    @RequestMapping(value = "/userprofile", method = RequestMethod.POST)
	public ResponseEntity<?> getProfile(@RequestBody String token) throws InterruptedException {

		System.out.println("IMA TOKEN: " + token);
		String email = tokenUtils.getUsernameFromToken(token);
		
		System.out.println("USERNAME: " + email);
	    User user = (User) this.userDetailsService.loadUserByUsername(email);
	    
	    System.out.println("Korisnik: " + user.getEmail() + "; id: " + user.getId());
	    
		return  new ResponseEntity<User>(user, HttpStatus.OK);
	}
    
   
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(){
    	
    	System.out.println("Logout agent back");
    	SecurityContextHolder.clearContext();

       
    }
    
    public void deleteFromTables() {

    	reservationService.deleteAll();
    	roomService.deleteAll();
    	priceForNightService.deleteAll();
    	userService.deleteAccommodationAgent();
    	userService.deleteUserRoles();
    	//userService.deleteAgents("agent");
    	messageService.deleteAll();
    	userService.deleteAllClients();
    	//permissionService.deleteRolePermission();
    	//permissionService.deleteAll();
    	//roleService.deleteAll();
    	accommodationService.deleteAll();
    	cancelationService.deleteAll();
    	accommodationTypeService.deleteAll();
    	additionalServiceService.deleteAll();
    }
    
    public void getMessages(Long agent_id) {
	    GetMessageResponse response = baseClient.getMessage(agent_id);
	    System.out.println("size of messages: " + response.getMessage().size());
	   
	    for(int i=0; i<response.getMessage().size(); i++) {
	    	messageService.save(response.getMessage().get(i));
    	}
    }
    
    public void getAccommodationTypes(Long agent_id) {
    	GetAccommodationTypeResponse response = baseClient.getAccommodationType(agent_id);
    	System.out.println("size of accommodation types: "  + response.getTypes().size());
    	
    	try {
    		for(int i=0; i<response.getTypes().size(); i++) {
        		accommodationTypeService.save(response.getTypes().get(i));
        	}
    	}catch(IllegalStateException e){
    		System.out.println("The object can not be saved.[getAccommodationType]");
    	}
    }
    
    public void getAdditionalService(Long agent_id) {
    	GetAdditionalServiceResponse response = baseClient.getAdditionalService(agent_id);
    	System.out.println("size of additional services: "  + response.getServices().size());
    	
		for(int i=0; i<response.getServices().size(); i++) {
			additionalServiceService.save(response.getServices().get(i));
    	}
    	
    }
    
    public void getAccommodation(Long agent_id) {
    	GetAccommodationResponse response = baseClient.getAccommodation(agent_id);
    	System.out.println("size of accommodations: "  + response.getAccommodation().size());
    	
		for(int i=0; i<response.getAccommodation().size(); i++) {
    		accommodationService.saveAccomodation(response.getAccommodation().get(i));
    	}
    	
    }
    
    public void getAgent(Long agent_id) {
    	GetAgentResponse response = baseClient.getAgent(agent_id);
    	System.out.println("size of agents: "  + response.getAgent().size());
    	
		for(int i=0; i<response.getAgent().size(); i++) {
			userService.saveAgent(response.getAgent().get(i));
    	}
    }
    
    public void getClient(Long agent_id) {
    	GetClientResponse response = baseClient.getClient(agent_id);
    	System.out.println("size of clients: "  + response.getClient().size());
    	
		for(int i=0; i<response.getClient().size(); i++) {
			userService.saveClient(response.getClient().get(i));
    	}
    }
    
    public void getPermission(Long agent_id) {
    	GetPermissionResponse response = baseClient.getPermission(agent_id);
    	System.out.println("size of permissions: "  + response.getPermission().size());
    	
		for(int i=0; i<response.getPermission().size(); i++) {
			permissionService.save(response.getPermission().get(i));
    	}
    }
    
    public void getRole(Long agent_id) {
    	GetRoleResponse response = baseClient.getRole(agent_id);
    	System.out.println("size of roles: "  + response.getRole().size());
    	
		for(int i=0; i<response.getRole().size(); i++) {
			if(!response.getRole().get(i).getName().equals("ROLE_ADMIN")) {
				System.out.println("role: " + response.getRole().get(i).getName());
				roleService.save(response.getRole().get(i));
			}
    	}
    }
    
    public void getCancelation(Long agent_id) {
    	GetCancelationResponse response = baseClient.getCancelation(agent_id);
    	System.out.println("size of cancelations: "  + response.getCancelations().size());
    	
		for(int i=0; i<response.getCancelations().size(); i++) {
			cancelationService.save(response.getCancelations().get(i));
    	}
    }
    
    public void getRoom(Long agent_id) {
    	GetRoomResponse response = baseClient.getRoom(agent_id);
    	System.out.println("size of rooms: "  + response.getRoom().size());
    	
		for(int i=0; i<response.getRoom().size(); i++) {
			System.out.println("accommodation id: " + response.getRoom().get(i).getAccommodation().getId());
			roomService.saveRoom(response.getRoom().get(i));
    	}
    }
    
    public void getPrice(Long agent_id) {
    	GetPriceResponse response = baseClient.getPrice(agent_id);
    	System.out.println("size of prices: "  + response.getPrices().size());
    	
		for(int i=0; i<response.getPrices().size(); i++) {
			priceForNightService.savePriceForNight(response.getPrices().get(i));
    	}
    }
    
    public void getReservation(Long agent_id) {
    	GetReservationResponse response = baseClient.getReservation(agent_id);
    	System.out.println("size of reservations: "  + response.getReservation().size());
    	
		for(int i=0; i<response.getReservation().size(); i++) {
			reservationService. save(response.getReservation().get(i));
    	}
    }

}
