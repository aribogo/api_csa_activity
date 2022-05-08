package api.gft.security.dto;

public class UserDTO {

	private String email;
	private String roleName;
	
	public UserDTO(String email, String roleName) {
		this.email = email;
		this.roleName = roleName;
	}

	public UserDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getroleName() {
		return roleName;
	}

	public void setroleName(String roleName) {
		this.roleName = roleName;
	}
	
}
