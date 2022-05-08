package api.gft.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api.gft.security.entity.User;
import api.gft.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Saves user in the database
	 * 
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Gets database user by email.
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		Optional<User> optional = userRepository.findByEmail(email);

		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}

		return optional.get();
	}

	/**
	 * Gets database user by id.
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}

		return optional.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserByEmail(username);
	}

}
