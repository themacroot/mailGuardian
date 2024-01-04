package com.sreekanth.mailGuardian.models;



public class SMTPConnectInput {

	String address;
	String domain;
	String fromEmail;
	String fromDomain;
	String mxAddr;
	String trace;
	

	
	
	
	public SMTPConnectInput(String address, String domain, String fromEmail, String fromDomain, String mxAddr,String trace) {
		super();
		this.address = address;
		this.domain = domain;
		this.fromEmail = fromEmail;
		this.fromDomain = fromDomain;
		this.mxAddr = mxAddr;
		this.trace = trace;
	}
	public SMTPConnectInput() {
		// TODO Auto-generated constructor stub
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
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the fromEmail
	 */
	public String getFromEmail() {
		return fromEmail;
	}
	/**
	 * @param fromEmail the fromEmail to set
	 */
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	/**
	 * @return the fromDomain
	 */
	public String getFromDomain() {
		return fromDomain;
	}
	/**
	 * @param fromDomain the fromDomain to set
	 */
	public void setFromDomain(String fromDomain) {
		this.fromDomain = fromDomain;
	}
	/**
	 * @return the mxAddr
	 */
	public String getMxAddr() {
		return mxAddr;
	}
	/**
	 * @param mxAddr the mxAddr to set
	 */
	public void setMxAddr(String mxAddr) {
		this.mxAddr = mxAddr;
	}
	
	
	
}
