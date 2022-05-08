package api.gft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.gft.entity.Csa;
import api.gft.entity.CsaParticipant;
import api.gft.entity.Participant;

public interface CsaParticipantRepository extends JpaRepository<CsaParticipant, Long> {

	/**
	 * Gets all records from database by CSA and that do not have a end date (end
	 * date determines whether a participant is part of a specific CSA).
	 * 
	 * @param csa
	 * @return List of all records with an specific CSA from tb_csa_participant
	 *         table.
	 */
	@Query(value = "SELECT d FROM CsaParticipant d WHERE d.csa = :csa AND d.endDate = null")
	public Page<CsaParticipant> findParticipantByCsa(Pageable pageable, @Param("csa") Csa csa);

	/**
	 * Gets CsaParticipant by participant.
	 * @param participantO
	 * @return
	 */
	@Query(value = "SELECT d FROM CsaParticipant d WHERE d.participant = :participant AND d.endDate = null")
	public CsaParticipant findByParticipant(@Param("participant") Participant participantO);

}
