package com.sreekanth.mailGuardian.models;

public class MailAttributes {

	/*
	 * Initializing variables with Zero Trust
	 */
	public boolean syntaxValid = false;
	public boolean dnsExists = false;
	public boolean mxExists = false;
	public Boolean mailboxExists = false;
	public boolean catchall = true;
	public boolean dmarcExists = false;
	public boolean spfExists = false;
	public boolean domainCreatedRecently = true;
	public boolean spam = true;
	public boolean disposible = true;
	public boolean roleAccount = true;
	public boolean freeEmail = false;
	/**
	 * @return the syntaxValid
	 */
	public boolean isSyntaxValid() {
		return syntaxValid;
	}
	/**
	 * @param syntaxValid the syntaxValid to set
	 */
	public void setSyntaxValid(boolean syntaxValid) {
		this.syntaxValid = syntaxValid;
	}
	/**
	 * @return the dnsExists
	 */
	public boolean isDnsExists() {
		return dnsExists;
	}
	/**
	 * @param dnsExists the dnsExists to set
	 */
	public void setDnsExists(boolean dnsExists) {
		this.dnsExists = dnsExists;
	}
	/**
	 * @return the mxExists
	 */
	public boolean isMxExists() {
		return mxExists;
	}
	/**
	 * @param mxExists the mxExists to set
	 */
	public void setMxExists(boolean mxExists) {
		this.mxExists = mxExists;
	}
	/**
	 * @return the mailboxExists
	 */
	public Boolean isMailboxExists() {
		return mailboxExists;
	}
	/**
	 * @param mailboxExists the mailboxExists to set
	 */
	public void setMailboxExists(Boolean mailboxExists) {
		this.mailboxExists = mailboxExists;
	}
	/**
	 * @return the catchall
	 */
	public boolean isCatchall() {
		return catchall;
	}
	/**
	 * @param catchall the catchall to set
	 */
	public void setCatchall(boolean catchall) {
		this.catchall = catchall;
	}
	/**
	 * @return the dmarcExists
	 */
	public boolean isDmarcExists() {
		return dmarcExists;
	}
	/**
	 * @param dmarcExists the dmarcExists to set
	 */
	public void setDmarcExists(boolean dmarcExists) {
		this.dmarcExists = dmarcExists;
	}
	/**
	 * @return the spfExists
	 */
	public boolean isSpfExists() {
		return spfExists;
	}
	/**
	 * @param spfExists the spfExists to set
	 */
	public void setSpfExists(boolean spfExists) {
		this.spfExists = spfExists;
	}
	/**
	 * @return the domainCreatedRecently
	 */
	public boolean isDomainCreatedRecently() {
		return domainCreatedRecently;
	}
	/**
	 * @param domainCreatedRecently the domainCreatedRecently to set
	 */
	public void setDomainCreatedRecently(boolean domainCreatedRecently) {
		this.domainCreatedRecently = domainCreatedRecently;
	}
	/**
	 * @return the spam
	 */
	public boolean isSpam() {
		return spam;
	}
	/**
	 * @param spam the spam to set
	 */
	public void setSpam(boolean spam) {
		this.spam = spam;
	}
	/**
	 * @return the disposible
	 */
	public boolean isDisposible() {
		return disposible;
	}
	/**
	 * @param disposible the disposible to set
	 */
	public void setDisposible(boolean disposible) {
		this.disposible = disposible;
	}
	/**
	 * @return the roleAccount
	 */
	public boolean isRoleAccount() {
		return roleAccount;
	}
	/**
	 * @param roleAccount the roleAccount to set
	 */
	public void setRoleAccount(boolean roleAccount) {
		this.roleAccount = roleAccount;
	}
	/**
	 * @return the freeEmail
	 */
	public boolean isFreeEmail() {
		return freeEmail;
	}
	/**
	 * @param freeEmail the freeEmail to set
	 */
	public void setFreeEmail(boolean freeEmail) {
		this.freeEmail = freeEmail;
	}
	public MailAttributes(boolean syntaxValid, boolean dnsExists, boolean mxExists, boolean mailboxExists,
			boolean catchall, boolean dmarcExists, boolean spfExists, boolean domainCreatedRecently, boolean spam,
			boolean disposible, boolean roleAccount, boolean freeEmail) {
		super();
		this.syntaxValid = syntaxValid;
		this.dnsExists = dnsExists;
		this.mxExists = mxExists;
		this.mailboxExists = mailboxExists;
		this.catchall = catchall;
		this.dmarcExists = dmarcExists;
		this.spfExists = spfExists;
		this.domainCreatedRecently = domainCreatedRecently;
		this.spam = spam;
		this.disposible = disposible;
		this.roleAccount = roleAccount;
		this.freeEmail = freeEmail;
	}
	@Override
	public String toString() {
		return "MailAttributes [syntaxValid=" + syntaxValid + ", dnsExists=" + dnsExists + ", mxExists=" + mxExists
				+ ", mailboxExists=" + mailboxExists + ", catchall=" + catchall + ", dmarcExists=" + dmarcExists
				+ ", spfExists=" + spfExists + ", domainCreatedRecently=" + domainCreatedRecently + ", spam=" + spam
				+ ", disposible=" + disposible + ", roleAccount=" + roleAccount + ", freeEmail=" + freeEmail + "]";
	}
	public MailAttributes() {
		super();
	}

	

}
