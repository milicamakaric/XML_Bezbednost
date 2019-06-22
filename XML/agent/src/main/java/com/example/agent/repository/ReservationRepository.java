package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agent.model.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
