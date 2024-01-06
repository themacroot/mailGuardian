package com.sreekanth.mailGuardian.models;

public class ApiError {
	
	String error;
	String status;
	String timeStamp;
	
	
	
	
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}




	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}




	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}




	public ApiError(String error, String status, String timeStamp) {
		super();
		this.error = error;
		this.status = status;
		this.timeStamp = timeStamp;
	}

}
