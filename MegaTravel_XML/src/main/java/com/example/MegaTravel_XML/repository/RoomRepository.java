package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
