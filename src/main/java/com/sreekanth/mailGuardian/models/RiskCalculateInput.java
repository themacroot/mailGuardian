package com.sreekanth.mailGuardian.models;

public class RiskCalculateInput {
	
	String email;
	String domain;
	String trace;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * @return the trace
	 */
	public String getTrace() {
		return trace;
	}
	/**
	 * @param trace the trace to set
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public RiskCalculateInput(String email, String domain, String trace) {
		super();
		this.email = email;
		this.domain = domain;
		this.trace = trace;
	}
	@Override
	public String toString() {
		return "RiskCalculateInput [email=" + email + ", domain=" + domain + ", trace=" + trace + "]";
	}


}
