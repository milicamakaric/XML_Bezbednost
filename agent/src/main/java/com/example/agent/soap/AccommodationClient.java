package com.example.agent.soap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.agent.model.Accommodation;
import com.example.agent.model.AccomodationRequest;
import com.example.agent.model.AccomodationResponse;

public class AccommodationClient extends WebServiceGatewaySupport{
	private static final Logger log = LoggerFactory.getLogger(AccommodationClient.class);
	public AccomodationResponse getCountry(Accommodation accom) {

		AccomodationRequest request = new AccomodationRequest();
		request.setAccommodation(accom);

		AccomodationResponse response = (AccomodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/accommodation", request,
						new SoapActionCallback(
								"http://megatravel.com/soap/soap/AccommodationRequest"));

		return response;
	}

}
