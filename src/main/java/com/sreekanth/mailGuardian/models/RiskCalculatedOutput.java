package com.sreekanth.mailGuardian.models;



public class RiskCalculatedOutput {

	
	String reputation;
	int score;
	String status;
	String remarks;
	MailAttributes mailAttributes = new MailAttributes();
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
	 * @return the mailAttributes
	 */
	public MailAttributes getMailAttributes() {
		return mailAttributes;
	}
	/**
	 * @param mailAttributes the mailAttributes to set
	 */
	public void setMailAttributes(MailAttributes mailAttributes) {
		this.mailAttributes = mailAttributes;
	}
	@Override
	public String toString() {
		return "RiskCalculatedOutput [reputation=" + reputation + ", score=" + score + ", status=" + status
				+ ", remarks=" + remarks + ", mailAttributes=" + mailAttributes + "]";
	}
	public RiskCalculatedOutput(String reputation, int score, String status, String remarks,
			MailAttributes mailAttributes) {
		super();
		this.reputation = reputation;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
		this.mailAttributes = mailAttributes;
	}
	
	
	public RiskCalculatedOutput() {
		super();

	}
	
} 
