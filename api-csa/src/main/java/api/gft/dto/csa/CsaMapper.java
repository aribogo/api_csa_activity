package api.gft.dto.csa;

import api.gft.entity.Csa;

public class CsaMapper {

	public static Csa fromDTO(CsaRegistrationDTO dto) {
		return new Csa(null, dto.getName());
	}

	public static CsaDTO fromEntity(Csa csa) {
		return new CsaDTO(csa.getId(), csa.getName());
	}

}
