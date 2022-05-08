package api.gft.entity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_participant")
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotEmpty(message = "Name can not be empty!")
	private String name;

	@NotEmpty(message = "E-mail can not be empty!")
	private String email;
	
	private Long idUser;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypePosition position;

	@OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
	private Set<CsaParticipant> csaParticipant;

	@OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
	private Set<Receipt> receipt;

	public Participant(Long id, String name, String email, TypePosition position, Set<CsaParticipant> csaParticipant, Long idUser) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.csaParticipant = csaParticipant;
		this.idUser = idUser;
	}

	public Participant(Long id, String name, String email, TypePosition position, CsaParticipant cParticipant,  Long idUser) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		csaParticipant = Stream.of(cParticipant).collect(Collectors.toSet());
		this.idUser = idUser;
	}

	public Participant(Long id) {
		this.id = id;
	}

	public Participant() {
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TypePosition getPosition() {
		return position;
	}

	public void setPosition(TypePosition position) {
		this.position = position;
	}

	public Set<CsaParticipant> getCsaParticipant() {
		return csaParticipant;
	}

	public void setCsaParticipant(Set<CsaParticipant> csaParticipant) {
		this.csaParticipant = csaParticipant;
	}

	public Set<Receipt> getReceipt() {
		return receipt;
	}

	public void setReceipt(Set<Receipt> receipt) {
		this.receipt = receipt;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	

}
