package com.example.MegaTravel_XML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MegaTravel_XML.dto.CommentDTO;
import com.example.MegaTravel_XML.model.Comment;

@RestController
@RequestMapping(value="api/comment")
@CrossOrigin(origins = "http://localhost:4200")

public class CommentController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PreAuthorize("hasAuthority('aproveComm')")
	@RequestMapping(value = "/aprove", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Comment comm) {
		
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comm.getId());
		commentDTO.setAllowed(true);
		
		String response = restTemplate.postForObject(
				"http://localhost:8553/approveComment",
				commentDTO, String.class);
		
		System.out.println("approveComment: " + response);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
		
		/*
		comm.setAllowed(true);
		Comment saved = commentService.saveComment(comm);
		return  new ResponseEntity<Comment>(saved, HttpStatus.OK);
		*/
	}

}

