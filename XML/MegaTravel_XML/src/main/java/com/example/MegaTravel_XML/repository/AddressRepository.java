package com.example.MegaTravel_XML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("select a " + 
			"from Address a  " + 
			"where a.longitude = :longitude and a.latitude = :latitude" )
	Address findAddress(@Param("longitude")double longitude,@Param("longitude")double latitude);
	
}
