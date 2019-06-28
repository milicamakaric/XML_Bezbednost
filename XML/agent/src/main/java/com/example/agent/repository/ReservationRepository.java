package com.example.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.agent.model.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@Transactional
	@Query(value="SELECT * FROM Reservation reservation WHERE reservation.room_id = ?1",  nativeQuery = true)
	List<Reservation> findByRoomId(Long id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Reservation res SET res.status = ?2 WHERE res.id = ?1", nativeQuery = true)
	void setStatus(Long id, String status);


}
