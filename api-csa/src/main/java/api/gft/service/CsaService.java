package api.gft.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import api.gft.entity.Csa;
import api.gft.repository.CsaRepository;

@Service
public class CsaService {

	private final CsaRepository csaRepository;

	// Constructors
	public CsaService(CsaRepository csaRepository) {
		this.csaRepository = csaRepository;
	}

	// Methods
	/**
	 * Saves CSA to database.
	 * @param csa
	 * @return
	 */
	public Csa saveCsa(Csa csa) {

		return csaRepository.save(csa);

	}

	/**
	 * Lists all database CSA or gives a not found message.
	 * @param pageable
	 * @return
	 */
	public Page<Csa> listAllCsas(Pageable pageable) {

		return csaRepository.findAll(pageable);
	}
	
	/**
	 * Gets specific CSA from database by id.
	 * @param id
	 * @return
	 */
	public Csa getCsa(Long id) {
		Optional<Csa> optional = csaRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Not found!"));
	}

	/**
	 * Updates CSA in database.
	 * @param updatedCsa
	 * @param id
	 * @return
	 */
	public Csa updateCsa(Csa updatedCsa, Long id) {

		Csa csa = this.getCsa(id);
		updatedCsa.setId(csa.getId());

		return csaRepository.save(updatedCsa);

	}

}
