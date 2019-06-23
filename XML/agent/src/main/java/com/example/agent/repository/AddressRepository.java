package com.example.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.agent.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{


	@Transactional
	@Query(value="SELECT * FROM Address address WHERE address.street = ?1 and address.number = ?2 and address.city = ?3 and address.ptt = ?4 and address.state = ?5",  nativeQuery = true)
	Address getByStreetNumberCityPTTState(String street, String number,
			String city, int ptt, String state);


}
