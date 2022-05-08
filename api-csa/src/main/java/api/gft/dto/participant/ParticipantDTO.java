package api.gft.dto.participant;

public class ParticipantDTO {

	private Long id;

	private String name;

	private String email;

	private String position;

	private String csaName;

	public ParticipantDTO(Long id, String name, String email, String position, String csaName) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.csaName = csaName;
	}

	public ParticipantDTO() {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCsaName() {
		return csaName;
	}

	public void setCsaName(String csaName) {
		this.csaName = csaName;
	}

}
