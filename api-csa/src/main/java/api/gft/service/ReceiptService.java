package api.gft.service;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.gft.FileUtility;
import api.gft.dto.Receipt.ReceiptDTO;
import api.gft.dto.Receipt.ReceiptMapper;
import api.gft.entity.Participant;
import api.gft.entity.Receipt;
import api.gft.repository.ReceiptRepository;

@Service
public class ReceiptService {
	
	private final ReceiptRepository receiptRepository;
	private final ParticipantService participantService;
	
	@Autowired
	private FileUtility fileU;
	
	public ReceiptService(ReceiptRepository receiptRepository, ParticipantService participantService) {
		this.receiptRepository = receiptRepository;
		this.participantService = participantService;
	}

	/**
	 * Tries to save a receipt in the database with a file. If the year and month reference exists throws exception. 
	 * @param file
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public Receipt saveReceipt(byte[] file, Receipt receipt) throws Exception {
		Participant p = participantService.getParticipantById(receipt.getParticipant().getId());
		receipt.setFileFolder( receipt.getMonthReference()+receipt.getYearReference());
		
		for(Receipt r : p.getReceipt()) {
			if(r.getMonthReference().equalsIgnoreCase(receipt.getMonthReference()) 
					&& r.getYearReference().equalsIgnoreCase(receipt.getYearReference())) {
				throw new Exception("You already paid this month's products.");
			}
		}
		
		String path = p.getId() + "/" + receipt.getFileFolder()+"/";
		fileU.saveFile(file, path, receipt.getFileName());
		
		return receiptRepository.save(receipt);		
		
	}
	
	/**
	 * Gets the receipt from database.
	 * @param receiptDto
	 * @return
	 */
	public ReceiptDTO getReveipt(ReceiptDTO receiptDto) {
		Receipt receipt = receiptRepository.getByParticipantMonth(receiptDto.getParticipantId(), receiptDto.getMonthReference(), receiptDto.getYearReference());
		receiptDto = ReceiptMapper.fromEntity(receipt);
		
		String path = receipt.getParticipant().getId() + "/" + receipt.getFileFolder()+"/";
		byte[] file = fileU.readFile(path, receipt.getFileName());

		receiptDto.setBase64Image( Base64.getMimeEncoder().encodeToString(file) );
		
		return receiptDto;
		
		
	}

}
