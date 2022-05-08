package api.gft.dto.csa;

public class CsaRegistrationDTO {

	private String name;

	public CsaRegistrationDTO(String name) {
		this.name = name;
	}

	public CsaRegistrationDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
