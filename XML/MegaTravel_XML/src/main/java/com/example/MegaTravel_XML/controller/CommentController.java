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

import com.example.MegaTravel_XML.model.Comment;
import com.example.MegaTravel_XML.services.CommentService;

@RestController
@RequestMapping(value="api/comment")
@CrossOrigin(origins = "http://localhost:4200")

public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PreAuthorize("hasAuthority('aproveComm')")
	@RequestMapping(value = "/aprove", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Comment comm) {
		comm.setAllowed(true);
		Comment saved = commentService.saveComment(comm);
		return  new ResponseEntity<Comment>(saved, HttpStatus.OK);
	}

}

