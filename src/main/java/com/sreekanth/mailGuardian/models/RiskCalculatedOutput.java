package com.sreekanth.mailGuardian.models;



public class RiskCalculatedOutput {

	
	String comments;
	int score;
	String status;
	String remarks;
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore(int score) {
		this.score = this.score + score;
	}
	
	public void decreaseScore(int score) {
		this.score = this.score - score;
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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void addRemarks(String remarks) {
		this.remarks = this.remarks + " | " + remarks;
	}
	

	public RiskCalculatedOutput(String comments, int score, String status, String remarks) {
		super();
		this.comments = comments;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	public RiskCalculatedOutput() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RiskCalculatedOutput [comments=" + comments + ", score=" + score + ", status=" + status + ", remarks="
				+ remarks + "]";
	}
	
	
	
} 
