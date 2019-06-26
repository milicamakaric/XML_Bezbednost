package com.example.MegaTravel_XML.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.AccommodationType;
import com.example.MegaTravel_XML.model.AdditionalService;
import com.example.MegaTravel_XML.model.Agent;
import com.example.MegaTravel_XML.model.Cancelation;
import com.example.MegaTravel_XML.model.Client;
import com.example.MegaTravel_XML.model.GetAccommodationRequest;
import com.example.MegaTravel_XML.model.GetAccommodationResponse;
import com.example.MegaTravel_XML.model.GetAccommodationTypeRequest;
import com.example.MegaTravel_XML.model.GetAccommodationTypeResponse;
import com.example.MegaTravel_XML.model.GetAdditionalServiceRequest;
import com.example.MegaTravel_XML.model.GetAdditionalServiceResponse;
import com.example.MegaTravel_XML.model.GetAgentRequest;
import com.example.MegaTravel_XML.model.GetAgentResponse;
import com.example.MegaTravel_XML.model.GetCancelationRequest;
import com.example.MegaTravel_XML.model.GetCancelationResponse;
import com.example.MegaTravel_XML.model.GetClientRequest;
import com.example.MegaTravel_XML.model.GetClientResponse;
import com.example.MegaTravel_XML.model.GetMessageRequest;
import com.example.MegaTravel_XML.model.GetMessageResponse;
import com.example.MegaTravel_XML.model.GetPermissionRequest;
import com.example.MegaTravel_XML.model.GetPermissionResponse;
import com.example.MegaTravel_XML.model.GetPriceRequest;
import com.example.MegaTravel_XML.model.GetPriceResponse;
import com.example.MegaTravel_XML.model.GetReservationRequest;
import com.example.MegaTravel_XML.model.GetReservationResponse;
import com.example.MegaTravel_XML.model.GetRoleRequest;
import com.example.MegaTravel_XML.model.GetRoleResponse;
import com.example.MegaTravel_XML.model.GetRoomRequest;
import com.example.MegaTravel_XML.model.GetRoomResponse;
import com.example.MegaTravel_XML.model.Message;
import com.example.MegaTravel_XML.model.Permission;
import com.example.MegaTravel_XML.model.PriceForNight;
import com.example.MegaTravel_XML.model.Reservation;
import com.example.MegaTravel_XML.model.Role;
import com.example.MegaTravel_XML.model.Room;
import com.example.MegaTravel_XML.services.AccommodationService;
import com.example.MegaTravel_XML.services.AccommodationTypeService;
import com.example.MegaTravel_XML.services.AdditionalServiceService;
import com.example.MegaTravel_XML.services.CancelationService;
import com.example.MegaTravel_XML.services.MessageService;
import com.example.MegaTravel_XML.services.PermissionService;
import com.example.MegaTravel_XML.services.PriceForNightService;
import com.example.MegaTravel_XML.services.ReservationService;
import com.example.MegaTravel_XML.services.RoleService;
import com.example.MegaTravel_XML.services.RoomService;
import com.example.MegaTravel_XML.services.UserService;

@Endpoint
public class BaseEndpoint {
	
private static final String NAMESPACE_URI = "http://megatravel.com/soap";
	
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
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationTypeRequest")
    @ResponsePayload
    public GetAccommodationTypeResponse getAccommodationType(@RequestPayload GetAccommodationTypeRequest request) {
		
		System.out.println("getAccommodationType in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetAccommodationTypeResponse response = new GetAccommodationTypeResponse();
		
		List<AccommodationType> types = accommodationTypeService.getTypes();
		
		response.setTypes(types);
 
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationRequest")
    @ResponsePayload
    public GetAccommodationResponse getAccommodation(@RequestPayload GetAccommodationRequest request) {
		
		System.out.println("getAccommodation in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetAccommodationResponse response = new GetAccommodationResponse();
		
		List<Accommodation> accommodations = accommodationService.getAll();
		response.setAccommodation(accommodations);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAdditionalServiceRequest")
    @ResponsePayload
    public GetAdditionalServiceResponse getAdditionalService(@RequestPayload GetAdditionalServiceRequest request) {
		
		System.out.println("getAdditionalService in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetAdditionalServiceResponse response = new GetAdditionalServiceResponse();
		
		List<AdditionalService> services = additionalServiceService.getServices();
		response.setServices(services);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPermissionRequest")
    @ResponsePayload
    public GetPermissionResponse getPermission(@RequestPayload GetPermissionRequest request) {
		
		System.out.println("getPermission in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetPermissionResponse response = new GetPermissionResponse();
		
		List<Permission> permissions = permissionService.getAll();
		response.setPermission(permissions);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAgentRequest")
    @ResponsePayload
    public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
		
		System.out.println("getAgent in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetAgentResponse response = new GetAgentResponse();
		
		List<Agent> agents = userService.getAllAgents();
		response.setAgent(agents);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClientRequest")
    @ResponsePayload
    public GetClientResponse getClient(@RequestPayload GetClientRequest request) {
		
		System.out.println("getClient in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetClientResponse response = new GetClientResponse();
		
		List<Client> clients = userService.getUsers();
		response.setClient(clients);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMessageRequest")
    @ResponsePayload
    public GetMessageResponse getMessage(@RequestPayload GetMessageRequest request) {
		
		System.out.println("getMessage in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetMessageResponse response = new GetMessageResponse();
		
		List<Message> messages = messageService.getAll();
		response.setMessage(messages);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetRoleRequest")
    @ResponsePayload
    public GetRoleResponse getMessage(@RequestPayload GetRoleRequest request) {
		
		System.out.println("getRole in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetRoleResponse response = new GetRoleResponse();
		
		List<Role> roles = roleService.getAll();
		response.setRole(roles);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCancelationRequest")
    @ResponsePayload
    public GetCancelationResponse getMessage(@RequestPayload GetCancelationRequest request) {
		
		System.out.println("getCancelation in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetCancelationResponse response = new GetCancelationResponse();
		
		List<Cancelation> cancelations = cancelationService.getAll();
		response.setCancelations(cancelations);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetRoomRequest")
    @ResponsePayload
    public GetRoomResponse getMessage(@RequestPayload GetRoomRequest request) {
		
		System.out.println("getRoom in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetRoomResponse response = new GetRoomResponse();
		
		List<Room> rooms = roomService.getAll();
		response.setRoom(rooms);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPriceRequest")
    @ResponsePayload
    public GetPriceResponse getPrice(@RequestPayload GetPriceRequest request) {
		
		System.out.println("getPrice in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetPriceResponse response = new GetPriceResponse();
		
		List<PriceForNight> prices = priceForNightService.getAll();
		response.setPrices(prices);
		
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetReservationRequest")
    @ResponsePayload
    public GetReservationResponse getReservation(@RequestPayload GetReservationRequest request) {
		
		System.out.println("getReservation in BaseEndpoint entered; agent_id: " + request.getAgentId());
		
		GetReservationResponse response = new GetReservationResponse();
		
		List<Reservation> reservations = reservationService.getAll();
		response.setReservation(reservations);
		
        return response;
    }

}
