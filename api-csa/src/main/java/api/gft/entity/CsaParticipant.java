package api.gft.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_csa_participant")
public class CsaParticipant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date", columnDefinition = "DATE")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="end_date",columnDefinition = "DATE")
	private LocalDate endDate;

	@NotNull
	@ManyToOne
	private Csa csa;

	@NotNull
	@ManyToOne
	private Participant participant;

	public CsaParticipant(Long id, LocalDate startDate, LocalDate endDate, Csa csa, Participant participant) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.csa = csa;
		this.participant = participant;
	}
	
	

	public CsaParticipant(Long id) {
		this.id = id;
	}



	public CsaParticipant() {
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
