package api.gft.dto.Receipt;

import java.time.LocalDate;

public class ReceiptRegistrationDTO {

	

	private Long participantId;
	
	private LocalDate paymentDate;
	
	private String monthReference;
	
	private String yearReference;
	
	private String fileFolder;
	
	private String fileName;
	
	private Long fileSize;
	
	private String fileType;
	
	private String base64Image;
	
		

	public ReceiptRegistrationDTO(Long participantId, LocalDate paymentDate, String monthReference, String yearReference, String fileFolder,
			String fileName, Long fileSize, String fileType) {
		this.participantId = participantId;
		this.paymentDate = paymentDate;
		this.monthReference = monthReference;
		this.yearReference = yearReference;
		this.fileFolder = fileFolder;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}
	
	public ReceiptRegistrationDTO() {}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getMonthReference() {
		return monthReference;
	}

	public void setMonthReference(String monthReference) {
		this.monthReference = monthReference;
	}

	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getYearReference() {
		return yearReference;
	}

	public void setYearReference(String yearReference) {
		this.yearReference = yearReference;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	

}
