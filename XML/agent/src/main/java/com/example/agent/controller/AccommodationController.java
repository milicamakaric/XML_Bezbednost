package com.example.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.dto.AccommodationDTO;
import com.example.agent.model.Accommodation;
import com.example.agent.model.Agent;
import com.example.agent.services.AccommodationService;


@RestController
@RequestMapping(value="accommodation")
@CrossOrigin(origins = "http://localhost:4202")
public class AccommodationController {
	
	@Autowired
	private AccommodationService accommodationService;
	
	@PreAuthorize("hasAuthority('getAgentAccommodation')")
	@RequestMapping(value="/getAccommodations/{id}", 
			method = RequestMethod.GET)
	public ResponseEntity<?> getAcommodationsOfAgent(@PathVariable("id") Long agent_id){
	
		System.out.println("Usao u accommodation controller - get acc of agents");
		List<Accommodation> allAcc = accommodationService.getAll();
		List<Accommodation> accOfAgent = new ArrayList<Accommodation>();
		
		for(int i=0; i<allAcc.size();i++)
		{
			Accommodation a = allAcc.get(i);
			if(a.getAgent().size()>0)
			{
				for(int j=0;j<a.getAgent().size();j++)
				{
					Agent ag = a.getAgent().get(j);
					if(ag.getId().equals(agent_id))
					{
						accOfAgent.add(a);
					}
				}
			}
		}
		
		System.out.println("Acc of agents: " + accOfAgent.size());
		List<AccommodationDTO> dtos = new ArrayList<AccommodationDTO>();
		
		for(Accommodation acc: accOfAgent)
		{
			System.out.println("Name " + acc.getName());
			
			AccommodationDTO dto = new AccommodationDTO(acc.getId(), acc.getName(), acc.getAddress().getStreet(), acc.getAddress().getNumber(), 
					acc.getAddress().getCity(), acc.getAddress().getState(),acc.getType().getName(), acc.getDescription());
			System.out.println("DTO " + dto.getId() + " " + dto.getName() + " " + dto.getStreet() + " " + dto.getNumber() + " " + dto.getCity() + " " + dto.getState() + " "
					+ dto.getType() + " " + dto.getDescription());
			dtos.add(dto);
			
		}
		return new ResponseEntity<List<AccommodationDTO>>(dtos, HttpStatus.OK);
	}

	
}
