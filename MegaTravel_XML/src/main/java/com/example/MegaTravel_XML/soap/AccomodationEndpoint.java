package com.example.MegaTravel_XML.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.Accommodation;
import com.example.MegaTravel_XML.model.AccomodationRequest;
import com.example.MegaTravel_XML.model.AccomodationResponse;
import com.example.MegaTravel_XML.services.AccommodationService;
import com.example.MegaTravel_XML.services.UserService;


@Endpoint
public class AccomodationEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
	
	@Autowired 
	private AccommodationService accomodationService;
	
	@Autowired 
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccommodationRequest")
    @ResponsePayload
    public AccomodationResponse createAccomodation(@RequestPayload AccomodationRequest request) {
		AccomodationResponse response = new AccomodationResponse();
		
		//naci usera preko mejla, pa onda naci sve acc kod kojih je taj user
		Accommodation acc = accomodationService.getAccommodationById(request.getAccommodation());
        response.setAccommodation(acc);
 
        return response;
    }

}
