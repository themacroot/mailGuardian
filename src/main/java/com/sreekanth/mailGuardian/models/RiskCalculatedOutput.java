package com.sreekanth.mailGuardian.models;



public class RiskCalculatedOutput {

	
	String reputation;
	int score;
	String status;
	String remarks;
	MailAttributes ma = new MailAttributes();
	/**
	 * @return the reputation
	 */
	public String getReputation() {
		return reputation;
	}
	/**
	 * @param reputation the reputation to set
	 */
	public void setReputation(String reputation) {
		this.reputation = reputation;
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
	/**
	 * @return the ma
	 */
	public MailAttributes getMa() {
		return ma;
	}
	/**
	 * @param ma the ma to set
	 */
	public void setMa(MailAttributes ma) {
		this.ma = ma;
	}
	public RiskCalculatedOutput(String reputation, int score, String status, String remarks, MailAttributes ma) {
		super();
		this.reputation = reputation;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
		this.ma = ma;
	}
	@Override
	public String toString() {
		return "RiskCalculatedOutput [reputation=" + reputation + ", score=" + score + ", status=" + status
				+ ", remarks=" + remarks + ", ma=" + ma + "]";
	}
	public RiskCalculatedOutput() {

	}
	public RiskCalculatedOutput(String string, int i, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	
	
	
} 
