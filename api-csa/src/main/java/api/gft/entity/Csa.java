package api.gft.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_csa")
public class Csa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name can not be empty!")
	private String name;

	@OneToMany(mappedBy = "csa")
	private Set<CsaParticipant> csaParticipant;

	//Constructors
	public Csa(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Csa() {
	}

	public Csa(Long id) {
		this.id = id;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CsaParticipant> getCsaParticipant() {
		return csaParticipant;
	}

	public void setCsaParticipant(Set<CsaParticipant> csaParticipant) {
		this.csaParticipant = csaParticipant;
	}
}
