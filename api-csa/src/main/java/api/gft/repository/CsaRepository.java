package api.gft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.gft.entity.Csa;

@Repository
public interface CsaRepository extends JpaRepository<Csa, Long>{

	/**
	 * Gets all database CSAs and paginates it.
	 */
	Page<Csa> findAll(Pageable pageable);
	
}
