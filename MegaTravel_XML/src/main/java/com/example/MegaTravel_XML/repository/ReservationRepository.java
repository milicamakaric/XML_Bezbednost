package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
