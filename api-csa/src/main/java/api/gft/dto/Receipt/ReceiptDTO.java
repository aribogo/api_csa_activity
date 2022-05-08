package api.gft.dto.Receipt;

import java.time.LocalDate;

public class ReceiptDTO {

	private Long id;

	private LocalDate paymentDate;

	private String participantName;

	private String documentType;

	private String base64Image;

	private String monthReference;

	private String yearReference;

	private Long participantId;


	public ReceiptDTO(Long id, LocalDate paymentDate, String participant, String base64Image, String documentType) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.participantName = participant;
		this.base64Image = base64Image;
		this.documentType = documentType;
	}
	
	

	public ReceiptDTO(Long id, LocalDate paymentDate, String participantName, String documentType,
			String monthReference, String yearReference) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.participantName = participantName;
		this.documentType = documentType;
		this.monthReference = monthReference;
		this.yearReference = yearReference;
	}



	public ReceiptDTO(String monthReference, String yearReference, Long participantId) {
		this.monthReference = monthReference;
		this.yearReference = yearReference;
		this.participantId = participantId;
	}

	public ReceiptDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getParticipant() {
		return participantName;
	}

	public void setParticipant(String participant) {
		this.participantName = participant;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getMonthReference() {
		return monthReference;
	}

	public void setMonthReference(String monthReference) {
		this.monthReference = monthReference;
	}

	public String getYearReference() {
		return yearReference;
	}

	public void setYearReference(String yearReference) {
		this.yearReference = yearReference;
	}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

}
