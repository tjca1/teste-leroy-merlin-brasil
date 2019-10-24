package br.com.tjca1.leroy.merlin.jms.message;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -4601533462989097404L;

	private Long processId;

	private String fileName;

	private byte[] fileContent;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	

}
