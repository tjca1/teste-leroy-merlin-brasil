package br.com.tjca1.leroy.merlin.rest.response;

public class RestResponse {

	private Long processId;

	private String message;

	public RestResponse(Long processId, String message) {
		super();
		this.processId = processId;
		this.message = message;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
