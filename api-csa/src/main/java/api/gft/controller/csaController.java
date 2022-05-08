package api.gft.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.gft.dto.csa.CsaDTO;
import api.gft.dto.csa.CsaMapper;
import api.gft.dto.csa.CsaRegistrationDTO;
import api.gft.entity.Csa;
import api.gft.entity.CsaParticipant;
import api.gft.entity.Participant;
import api.gft.security.entity.User;
import api.gft.service.CsaService;
import api.gft.service.ParticipantService;

@RestController
@RequestMapping("v1/csa")
public class csaController{

	private final CsaService csaService;

	@Autowired
	private ParticipantService participantService;

	// Constructor
	public csaController(CsaService csaService) {
		this.csaService = csaService;
	}

	// Methods
	/**
	 * Authenticates user and if allowed saves new CSA into the database or gives
	 * Exception error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param dto
	 * @return ResponseEntity<CsaDTO>
	 */
	@PostMapping("{id}")
	public ResponseEntity<?> saveCsa(@RequestBody CsaRegistrationDTO dto, Long id, Locale locale) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;
		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(id)) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application");
		}

		try {

			Csa csa = csaService.saveCsa(CsaMapper.fromDTO(dto));
			return ResponseEntity.ok(CsaMapper.fromEntity(csa));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("We could not save this CSA.");
		}

	}

	/**
	 * Lists all database CSAs and paginates it according to the number the user or
	 * gives Exception error. chooses to display on the screen.
	 * 
	 * @param pageable
	 * @return ResponseEntity<CsaDTO>
	 */
	@GetMapping
	public ResponseEntity<Page<CsaDTO>> getAllCsas(Pageable pageable) {
		return ResponseEntity.ok(csaService.listAllCsas(pageable).map(CsaMapper::fromEntity));
	}

	/**
	 * Authenticates user and if allowed updates the value of a CSA or gives
	 * Exception error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param dto
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateCsa(@RequestBody CsaRegistrationDTO dto, @PathVariable Long id, @PathVariable Locale locale) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;
		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(id)) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application.");
		}

		try {
			Csa updatedCsa = csaService.updateCsa(CsaMapper.fromDTO(dto), id);

			return ResponseEntity.ok(CsaMapper.fromEntity(updatedCsa));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to find this CSA.");
		}

	}

}
