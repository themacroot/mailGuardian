package com.sreekanth.mailGuardian.models;

public class SMTPConnectOutput {
	
	String serverResponse;
	String status;
	String action;
	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}
	/**
	 * @param serverResponse the serverResponse to set
	 */
	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "SMTPConnectOutput [serverResponse=" + serverResponse + ", status=" + status + ", action=" + action
				+ "]";
	}
	
	

}