package com.example.MegaTravel_XML.services;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Comment;

@Service
public interface CommentService {

	Comment saveComment(Comment comment);
}
