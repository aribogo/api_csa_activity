package api.gft.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.gft.entity.Csa;
import api.gft.entity.CsaParticipant;
import api.gft.entity.Participant;
import api.gft.message.exception.ExceptionHandlerEx;
import api.gft.repository.CsaParticipantRepository;
import api.gft.repository.ParticipantRepository;
import api.gft.security.entity.Role;
import api.gft.security.entity.User;
import api.gft.security.repository.RoleRepository;
import api.gft.security.service.UserService;

@Service
public class ParticipantService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	private final ParticipantRepository participantRepository;
	private final CsaParticipantRepository csaParticipantRepository;

	public ParticipantService(ParticipantRepository participantRepository,
			CsaParticipantRepository csaParticipantRepository) {
		this.participantRepository = participantRepository;
		this.csaParticipantRepository = csaParticipantRepository;
	}

	/**
	 * Tries to save a participant and user in the database. Or throws exception.
	 * 
	 * @param participant
	 * @param idCsa
	 * @return
	 * @throws Exception
	 */
	public Participant saveParticipant(Participant participant, Long idCsa) throws Exception {

		Csa csa = new Csa();
		csa.setId(idCsa);

		Optional<Participant> p = null;

		CsaParticipant csaParticipant = new CsaParticipant();
		csaParticipant.setCsa(csa);
		csaParticipant.setStartDate(LocalDate.now());
		csaParticipant.setParticipant(participant);
		Set<CsaParticipant> cpSet = new HashSet<>();
		cpSet.add(csaParticipant);

		participant.setCsaParticipant(cpSet);
		if (csa.getId() != null) {

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String randomPass = alphaNumericString(10);
			System.out.println(randomPass);

			Role role = null;
			List<Role> roles = roleRepository.findByName(participant.getPosition().toString());
			for (Role r : roles) {
				if (r.getName().equalsIgnoreCase(participant.getPosition().toString())) {
					role = r;
					break;
				}
			}

			User user = new User(null, participant.getEmail(), passwordEncoder.encode(randomPass), role);
			user = userService.saveUser(user);
			participant.setIdUser(user.getId());

			participant = participantRepository.save(participant);

		} else {
			return p.orElseThrow(() -> new ExceptionHandlerEx("There may be fields null that can not be null"));
		}

		return participant;

	}

	/**
	 * Password builder
	 * 
	 * @param len
	 * @return
	 */
	private static String alphaNumericString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	/**
	 * Gets a database participant by id.
	 * 
	 * @param id
	 * @return
	 */
	public Participant getParticipantById(Long id) {
		Optional<Participant> optional = participantRepository.findById(id);
		return optional.orElseThrow(() -> new ExceptionHandlerEx("Not Found!"));
	}

	/**
	 * Gets a database participant by userId.
	 * 
	 * @param idUser
	 * @return
	 */
	public Participant getParticipantByIdUser(Long idUser) {
		Participant participant = participantRepository.findByUserId(idUser);
		return participant;
	}

	/**
	 * Gets all database participants and paginates them according to the size
	 * chosen by the user in the API.
	 * 
	 * @param pageable
	 * @param csa_id
	 * @return
	 */
	public Page<CsaParticipant> getAllCsaParticipantsByCSAAndEndDate(Pageable pageable, Long csa_id) {

		Csa csa = new Csa();
		csa.setId(csa_id);

		Page<CsaParticipant> csaParticipant = csaParticipantRepository.findParticipantByCsa(pageable, csa);

		return csaParticipant;
	}

	/**
	 * Updates participant information by id.
	 *  If there is a change in the CSA, it deactivates the link with the past CSA,
	 *   placing an end date and creates a new link with the new CSA chosen. 
	 * @param participant
	 * @param idParticipant
	 * @param idCsa
	 * @return
	 */
	public Participant updateParticipant(Participant participant, Long idParticipant, Long idCsa) {

		Csa csa = new Csa();
		csa.setId(idCsa);

		Participant participantO = this.getParticipantById(idParticipant);
		participantO.setName(participant.getName());
		participantO.setPosition(participant.getPosition());

		CsaParticipant cpOriginal = csaParticipantRepository.findByParticipant(participantO);
		if (cpOriginal.getCsa().getId() != csa.getId()) {
			cpOriginal.setEndDate(LocalDate.now());
			csaParticipantRepository.save(cpOriginal);

			CsaParticipant csaParticipant = new CsaParticipant();
			csaParticipant.setCsa(csa);
			csaParticipant.setStartDate(LocalDate.now());
			csaParticipant.setParticipant(participantO);
			participantO.getCsaParticipant().add(csaParticipant);
			participantRepository.save(participantO);
		}

		return participantO;

	}

	/**
	 * Deactivates user by setting a endDate in the database.
	 * 
	 * @param id
	 */
	public void inactivateParticipant(Long id) {

		Optional<Participant> p = participantRepository.findById(id);

		CsaParticipant cp = csaParticipantRepository.findByParticipant(p.get());
		cp.setEndDate(LocalDate.now());

		csaParticipantRepository.save(cp);
	}

}
