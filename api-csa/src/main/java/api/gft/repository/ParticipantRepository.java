package api.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import api.gft.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	
	/**
	 * Gets participant by user id.
	 * @param idUser
	 * @return
	 */
	@Query(value = "SELECT p FROM Participant p WHERE p.idUser = :idUser")
	public Participant findByUserId(@RequestParam("idUser") Long idUser);
}
