package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaTravel_XML.model.reservation.Cancelation;

@Repository
public interface CancelationRepository extends JpaRepository<Cancelation, Long>{

}
