package api.gft.dto.participantCsa;

import java.time.LocalDate;
import api.gft.entity.Csa;
import api.gft.entity.Participant;

public class ParticipantCsaDTO {

	private Long id;

	private LocalDate startDate;

	private LocalDate endDate;

	private Csa csa;

	private Participant participant;

	public ParticipantCsaDTO(Long id, LocalDate startDate, LocalDate endDate, Csa csa, Participant participant) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.csa = csa;
		this.participant = participant;
	}

	public ParticipantCsaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Csa getCsa() {
		return csa;
	}

	public void setCsa(Csa csa) {
		this.csa = csa;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
