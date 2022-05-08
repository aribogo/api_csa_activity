package api.gft.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.gft.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Gets user by email.
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
}
