package api.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import api.gft.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

	/**
	 * Gets receipt by participant id, reference month and year. 
	 * @param participantId
	 * @param monthReference
	 * @param yearReference
	 * @return
	 */
	@Query(value = "SELECT p "
			+ "FROM Receipt p "
			+ "WHERE p.participant.id = :participantId AND p.monthReference = :monthReference AND p.yearReference = :yearReference")
	public Receipt getByParticipantMonth(@RequestParam("participantId") Long participantId,
			@RequestParam("monthReference") String monthReference,  @RequestParam("yearReference") String yearReference);

}
