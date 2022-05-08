package api.gft.dto.csa;

public class CsaDTO {

	private Long id;

	private String name;

	public CsaDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CsaDTO() {
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

}
