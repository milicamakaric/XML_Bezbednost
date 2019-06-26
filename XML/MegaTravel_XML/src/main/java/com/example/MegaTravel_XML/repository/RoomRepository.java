package com.example.MegaTravel_XML.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.MegaTravel_XML.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	@Transactional
	@Query(value="SELECT * FROM Room room WHERE room.accommodation_id = ?1",  nativeQuery = true)
	List<Room> findByAccommodationId(Long id);

}
