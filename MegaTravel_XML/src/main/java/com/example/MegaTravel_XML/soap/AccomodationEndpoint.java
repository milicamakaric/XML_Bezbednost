package com.example.MegaTravel_XML.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.MegaTravel_XML.model.AccomodationRequest;
import com.example.MegaTravel_XML.model.AccomodationResponse;
import com.example.MegaTravel_XML.repository.AccommodationRepository;
import com.example.MegaTravel_XML.repository.UserRepository;


@Endpoint
public class AccomodationEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
	
	@Autowired 
	private AccommodationRepository accomodationRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccommodationRequest")
    @ResponsePayload
    public AccomodationResponse createAccomodation(@RequestPayload AccomodationRequest request) {
		AccomodationResponse response = new AccomodationResponse();
		
		//naci usera preko mejla, pa onda naci sve acc kod kojih je taj user
        //response.setAccommodation(userRepository.findOneById(request.getAgent());
 
        return response;
    }

}
