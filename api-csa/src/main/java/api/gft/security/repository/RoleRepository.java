package api.gft.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.gft.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	/**
	 * Gets role by name.
	 * @param name
	 * @return
	 */
	List<Role> findByName(String name);

}
