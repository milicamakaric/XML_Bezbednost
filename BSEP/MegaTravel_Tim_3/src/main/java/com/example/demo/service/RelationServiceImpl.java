package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Relation;
import com.example.demo.repository.RelationRepository;

@Service
public class RelationServiceImpl implements RelationService {
	@Autowired 
	RelationRepository relationRepository;
	@Override
	public List<Relation> getAll() {
		// TODO Auto-generated method stub
		return relationRepository.findAll();
	}
	@Override
	public Relation saveRelation(Relation relation) {
		// TODO Auto-generated method stub
		return relationRepository.save(relation);
	}

}
