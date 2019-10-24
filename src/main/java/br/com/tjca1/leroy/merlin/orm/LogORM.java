package br.com.tjca1.leroy.merlin.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMPORT_SHEET_LOG")
public class LogORM implements Serializable {

	private static final long serialVersionUID = -6036711932585075429L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "START_PROCESS_DATE")
	private Date startProcessDate;

	@Column(name = "END_PROCESS_DATE")
	private Date endProcessDate;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "MESSAGE_ERROR")
	private String messageError;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStartProcessDate() {
		return startProcessDate;
	}

	public void setStartProcessDate(Date startProcessDate) {
		this.startProcessDate = startProcessDate;
	}

	public Date getEndProcessDate() {
		return endProcessDate;
	}

	public void setEndProcessDate(Date endProcessDate) {
		this.endProcessDate = endProcessDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	

}
