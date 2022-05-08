package api.gft.controller;

import java.util.Base64;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import api.gft.dto.Receipt.ReceiptDTO;
import api.gft.dto.Receipt.ReceiptMapper;
import api.gft.dto.Receipt.ReceiptRegistrationDTO;
import api.gft.dto.participant.ParticipantDTO;
import api.gft.dto.participant.ParticipantMapper;
import api.gft.dto.participant.ParticipantRegistrationDTO;
import api.gft.entity.Csa;
import api.gft.entity.CsaParticipant;
import api.gft.entity.Participant;
import api.gft.entity.TypePosition;
import api.gft.security.entity.User;
import api.gft.service.ParticipantService;
import api.gft.service.ReceiptService;

@RestController
@RequestMapping("v1/participant")
public class ParticipantController {

	private final ParticipantService participantService;
	private final ReceiptService receiptService;

	public ParticipantController(ParticipantService participantService, ReceiptService receiptService) {
		this.participantService = participantService;
		this.receiptService = receiptService;
	}

	/**
	 * Authenticates user and if allowed saves new participant into the database or
	 * gives Exception error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveParticipant(@RequestBody ParticipantRegistrationDTO dto) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;
		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(dto.getIdCsa())) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application.");
		}

		Participant participant = null;

		try {
			participant = participantService.saveParticipant(ParticipantMapper.fromDTO(dto), dto.getIdCsa());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("We coudn't save the new participant. Verify if you filled in the fields correctly.");
		}

		return ResponseEntity.ok(ParticipantMapper.fromEntity(participant));
	}

	/**
	 * Authenticates user and if allowed gets all database participant or gives not
	 * allowed message (403 - FORBIDDEN).
	 * 
	 * @param pageable
	 * @param idCsa
	 * @return
	 */
	@GetMapping("{idCsa}")
	public ResponseEntity<Page<ParticipantDTO>> getAllCsaPacipants(@PageableDefault Pageable pageable,
			@PathVariable Long idCsa) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;
		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(idCsa)) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(403).build();
		}

		return ResponseEntity.ok(participantService.getAllCsaParticipantsByCSAAndEndDate(pageable, idCsa)
				.map(ParticipantMapper::fromCsaParticipantEntity));
	}

	/**
	 * Authenticates user and if allowed updates participant or gives Exception
	 * error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param dto
	 * @param id
	 * @param idCsa
	 * @return
	 */
	@PutMapping("{id}/{idCsa}")
	public ResponseEntity<?> updateParticipant(@RequestBody ParticipantRegistrationDTO dto, @PathVariable Long id,
			@PathVariable Long idCsa) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;
		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(idCsa)) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application.");
		}

		Participant participant = null;

		try {
			participant = participantService.updateParticipant(ParticipantMapper.fromDTO(dto), id, dto.getIdCsa());
			return ResponseEntity.ok(ParticipantMapper.fromEntity(participant));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("We coudn't update the new participant. Verify if you filled in the fields correctly.");
		}
	}

	/**
	 * Authenticates user and if allowed deactivates participant or gives Exception
	 * error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> inactivateParticipant(@PathVariable Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		Participant p = participantService.getParticipantByIdUser(principal.getId());
		Boolean validade = false;

		Participant pToGetCsa = participantService.getParticipantById(id);
		Csa csa = new Csa();

		for (CsaParticipant cp : pToGetCsa.getCsaParticipant()) {
			if (cp.getEndDate() == null) {
				csa.setId(cp.getCsa().getId());
			}
		}

		for (CsaParticipant cp : p.getCsaParticipant()) {
			if (cp.getCsa().getId().equals(csa.getId())) {
				validade = true;
			}
		}

		if (!validade) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application.");
		}

		try {
			participantService.inactivateParticipant(id);

			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("We coudn't inactivate new participant. Verify if you filled in the fields correctly.");
		}

	}

	/**
	 * Authenticates user and if allowed saves new receipt into the database or
	 * gives Exception error, if not allowed gives 403 message (FORBIDDEN).
	 * 
	 * @param dto
	 * @param id
	 * @return
	 */
	@PostMapping(path = "receipt/{id}", produces = "application/json")
	public ResponseEntity<ReceiptDTO> saveReceipt(@RequestBody ReceiptRegistrationDTO dto, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();
		Participant pSave = participantService.getParticipantByIdUser(principal.getId());

		if (!pSave.getId().equals(id)) {
			return ResponseEntity.status(403).build();
		}

		try {
			byte[] imageByte = Base64.getMimeDecoder().decode(dto.getBase64Image());
			receiptService.saveReceipt(imageByte, ReceiptMapper.fromDTO(dto));

		} catch (Exception e) {
			return ResponseEntity.status(417).build();
		}

		return null;

	}

	@GetMapping(path = "receipt/{participantId}/{monthReference}/{yearReference}")
	public ResponseEntity<?> getReceipt(@PathVariable Long participantId, @PathVariable String monthReference,
			@PathVariable String yearReference) {

		
		Participant p1 = participantService.getParticipantById(participantId);	
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		
		Boolean validate = true;
		if(p1.getIdUser().equals(principal.getId())) {
			validate = true;
		}
		
		

		if (!validate) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("You do not have access to this part of the application.");
		}

		ReceiptDTO dto = new ReceiptDTO(monthReference, yearReference, participantId);
		try {
			return ResponseEntity.ok(receiptService.getReveipt(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to find this receipt.");
		}
	}

}
