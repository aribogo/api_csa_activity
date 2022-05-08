package api.gft.dto.Receipt;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import api.gft.FileUtility;
import api.gft.entity.Participant;
import api.gft.entity.Receipt;

public class ReceiptMapper {
	
	@Autowired
	private static FileUtility fileU;

	public static Receipt fromDTO(ReceiptRegistrationDTO dto) {

		Participant p = new Participant(dto.getParticipantId());

		return new Receipt(null, dto.getPaymentDate(), dto.getMonthReference(), dto.getYearReference(),
				dto.getFileFolder(), dto.getFileName(), dto.getFileSize(), dto.getFileType(), p);

	}

	public static ReceiptDTO fromEntity(Receipt receipt) {


		 return new ReceiptDTO(receipt.getId(), receipt.getPaymentDate(), receipt.getParticipant().getName(), receipt.getFileType(), receipt.getMonthReference(), receipt.getYearReference());
	}

}
