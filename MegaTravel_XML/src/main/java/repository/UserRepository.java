package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.accomodation.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
