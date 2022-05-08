package api.gft.dto.participant;

import api.gft.entity.Csa;
import api.gft.entity.CsaParticipant;
import api.gft.entity.Participant;
import api.gft.entity.TypePosition;

public class ParticipantMapper {

	public static Participant fromDTO(ParticipantRegistrationDTO dto) {
		CsaParticipant csaParticipant = new CsaParticipant();
		csaParticipant.setCsa(new Csa(dto.getIdCsa()));
		return new Participant(null, dto.getName(), dto.getEmail(), TypePosition.getEnum(dto.getPosition()),
				csaParticipant, dto.getIdUser());
	}

	public static ParticipantDTO fromEntity(Participant participant) {

		Csa csa = new Csa();

		for (CsaParticipant cp : participant.getCsaParticipant()) {
			csa.setId(cp.getCsa().getId());
		}
		return new ParticipantDTO(participant.getId(), participant.getName(), participant.getEmail(),
				participant.getPosition().getDisplayValue(), csa.getName());
	}

	public static ParticipantDTO fromCsaParticipantEntity(CsaParticipant csaParticipant) {

		Csa csa = new Csa();
		csa.setName(csaParticipant.getCsa().getName());
		String csaName = csa.getName();
		return new ParticipantDTO(csaParticipant.getParticipant().getId(), csaParticipant.getParticipant().getName(),
				csaParticipant.getParticipant().getEmail(),
				csaParticipant.getParticipant().getPosition().getDisplayValue(), csaName);
	}

}
