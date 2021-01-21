package com.mypractice.sb.CabServiceSystem.bo;

public class GenericSuccessResponse {
	
	private String status;
	
	public GenericSuccessResponse() {
		
	}

	public GenericSuccessResponse(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
