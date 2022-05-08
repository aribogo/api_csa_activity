package api.gft.security.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import api.gft.entity.Csa;
import api.gft.security.entity.Role;
import api.gft.security.entity.User;

public class UserMapper {

	public static User fromDTO(UserRegistrationDTO dto) {

		Role role = new Role();
		role.setId(dto.getIdRole());

		Csa csa = new Csa();
		csa.setId(dto.getIdCsa());

		return new User(null, dto.getEmail(), new BCryptPasswordEncoder().encode(dto.getPassword()), role);
	}

	public static UserDTO fromEntity(User user) {

		return new UserDTO(user.getEmail(), user.getrole().getName());

	}

}
