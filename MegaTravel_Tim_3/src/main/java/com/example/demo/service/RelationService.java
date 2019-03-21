package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.model.Relation;

@Service
public interface RelationService {
	List<Relation> getAll();

}
