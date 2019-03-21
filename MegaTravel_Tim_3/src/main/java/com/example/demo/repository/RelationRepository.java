package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Relation;

public interface RelationRepository extends JpaRepository<Relation,Long> {

}
