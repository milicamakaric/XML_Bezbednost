package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}

