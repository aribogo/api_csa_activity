package api.gft.dto.participant;

public class ParticipantRegistrationDTO {

	private String name;

	private String email;

	private String position;

	private Long idCsa;
	private Long idUser;

	public ParticipantRegistrationDTO(String name, String email, String position, Long idCsa) {
		this.name = name;
		this.email = email;
		this.position = position;
		this.idCsa = idCsa;
	}

	public ParticipantRegistrationDTO() {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getIdCsa() {
		return idCsa;
	}

	public void setIdCsa(Long idCsa) {
		this.idCsa = idCsa;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
