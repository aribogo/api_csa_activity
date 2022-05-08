package api.gft.security.dto;

public class UserRegistrationDTO {

	private String email;

	private String password;

	private Long id_role;

	private Long id_csa;

	public UserRegistrationDTO(String email, String password, Long role, Long csa) {
		this.email = email;
		this.password = password;
		this.id_role = role;
		this.id_csa = csa;
	}

	public UserRegistrationDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIdRole() {
		return id_role;
	}

	public void setIdRole(Long role) {
		this.id_role = role;
	}

	public Long getIdCsa() {
		return id_csa;
	}

	public void setIdCsa(Long csa) {
		this.id_csa = csa;
	}

}
