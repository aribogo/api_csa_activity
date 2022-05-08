package api.gft.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_receipt")
public class Receipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate paymentDate;
	
	private String monthReference;
	
	private String yearReference;
	
	private String fileFolder;
	
	private String fileName;
	
	private Long fileSize;
	
	private String fileType;
	
	@ManyToOne
	private Participant participant;



	public Receipt(Long id, LocalDate paymentDate, String monthReference, String yearReference, String fileFolder, String fileName,
			Long fileSize, String fileType, Participant participant) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.monthReference = monthReference;
		this.yearReference = yearReference;
		this.fileFolder = fileFolder;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.participant = participant;
	}

	public Receipt() {
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

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
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



}
