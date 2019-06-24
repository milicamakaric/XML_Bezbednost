package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Comment;
import com.example.MegaTravel_XML.repository.CommentRepository;


@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}
}
