package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.accomodation.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
