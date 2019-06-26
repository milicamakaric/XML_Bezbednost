package com.example.agent.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.agent.model.GetAccommodationRequest;
import com.example.agent.model.GetAccommodationResponse;
import com.example.agent.model.GetAccommodationTypeRequest;
import com.example.agent.model.GetAccommodationTypeResponse;
import com.example.agent.model.GetAdditionalServiceRequest;
import com.example.agent.model.GetAdditionalServiceResponse;
import com.example.agent.model.GetAgentRequest;
import com.example.agent.model.GetAgentResponse;
import com.example.agent.model.GetCancelationRequest;
import com.example.agent.model.GetCancelationResponse;
import com.example.agent.model.GetClientRequest;
import com.example.agent.model.GetClientResponse;
import com.example.agent.model.GetMessageRequest;
import com.example.agent.model.GetMessageResponse;
import com.example.agent.model.GetPermissionRequest;
import com.example.agent.model.GetPermissionResponse;
import com.example.agent.model.GetPriceRequest;
import com.example.agent.model.GetPriceResponse;
import com.example.agent.model.GetReservationRequest;
import com.example.agent.model.GetReservationResponse;
import com.example.agent.model.GetRoleRequest;
import com.example.agent.model.GetRoleResponse;
import com.example.agent.model.GetRoomRequest;
import com.example.agent.model.GetRoomResponse;

public class BaseClient extends WebServiceGatewaySupport {
	
	public GetAccommodationTypeResponse getAccommodationType(Long agent_id) {

		GetAccommodationTypeRequest request = new GetAccommodationTypeRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getAccommodationType in BaseClient entered");

		GetAccommodationTypeResponse response = (GetAccommodationTypeResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetAccommodationResponse getAccommodation(Long agent_id) {

		GetAccommodationRequest request = new GetAccommodationRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getAccommodation in BaseClient entered");

		GetAccommodationResponse response = (GetAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetAdditionalServiceResponse getAdditionalService(Long agent_id) {

		GetAdditionalServiceRequest request = new GetAdditionalServiceRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getAdditionalService in BaseClient entered");

		GetAdditionalServiceResponse response = (GetAdditionalServiceResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetPermissionResponse getPermission(Long agent_id) {

		GetPermissionRequest request = new GetPermissionRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getPermission in BaseClient entered");

		GetPermissionResponse response = (GetPermissionResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetRoleResponse getRole(Long agent_id) {

		GetRoleRequest request = new GetRoleRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getRole in BaseClient entered");

		GetRoleResponse response = (GetRoleResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetAgentResponse getAgent(Long agent_id) {

		GetAgentRequest request = new GetAgentRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getAgent in BaseClient entered");

		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetClientResponse getClient(Long agent_id) {

		GetClientRequest request = new GetClientRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getClient in BaseClient entered");

		GetClientResponse response = (GetClientResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetMessageResponse getMessage(Long agent_id) {

		GetMessageRequest request = new GetMessageRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getMessage in BaseClient entered");

		GetMessageResponse response = (GetMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetCancelationResponse getCancelation(Long agent_id) {

		GetCancelationRequest request = new GetCancelationRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getCancelation in BaseClient entered");

		GetCancelationResponse response = (GetCancelationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetRoomResponse getRoom(Long agent_id) {

		GetRoomRequest request = new GetRoomRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getRoom in BaseClient entered");

		GetRoomResponse response = (GetRoomResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetPriceResponse getPrice(Long agent_id) {

		GetPriceRequest request = new GetPriceRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getPrice in BaseClient entered");

		GetPriceResponse response = (GetPriceResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}
	
	public GetReservationResponse getReservation(Long agent_id) {

		GetReservationRequest request = new GetReservationRequest();
		request.setAgentId(agent_id);
		
		System.out.println("getReservation in BaseClient entered");

		GetReservationResponse response = (GetReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8084/megatravelxml/ws", request);

		return response;
	}

}
