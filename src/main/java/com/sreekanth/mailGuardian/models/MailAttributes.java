package com.sreekanth.mailGuardian.models;

public class MailAttributes {
public boolean isSyntaxValid;
public boolean dnsExists;
public boolean mxExists;
public boolean mailboxExists;
public boolean isCatchall;
public boolean dmarcExists;
public boolean spfExists;
public boolean domainCreatedRecently;
public boolean isSpam;
public boolean isDisposible;
public boolean isRoleAccount;
public boolean isFreeEmail;
/**
 * @return the isSyntaxValid
 */
public boolean isSyntaxValid() {
	return isSyntaxValid;
}
/**
 * @param isSyntaxValid the isSyntaxValid to set
 */
public void setSyntaxValid(boolean isSyntaxValid) {
	this.isSyntaxValid = isSyntaxValid;
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
public boolean isMailboxExists() {
	return mailboxExists;
}
/**
 * @param mailboxExists the mailboxExists to set
 */
public void setMailboxExists(boolean mailboxExists) {
	this.mailboxExists = mailboxExists;
}
/**
 * @return the isCatchall
 */
public boolean isCatchall() {
	return isCatchall;
}
/**
 * @param isCatchall the isCatchall to set
 */
public void setCatchall(boolean isCatchall) {
	this.isCatchall = isCatchall;
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
 * @return the isSpam
 */
public boolean isSpam() {
	return isSpam;
}
/**
 * @param isSpam the isSpam to set
 */
public void setSpam(boolean isSpam) {
	this.isSpam = isSpam;
}
/**
 * @return the isDisposible
 */
public boolean isDisposible() {
	return isDisposible;
}
/**
 * @param isDisposible the isDisposible to set
 */
public void setDisposible(boolean isDisposible) {
	this.isDisposible = isDisposible;
}
/**
 * @return the isRoleAccount
 */
public boolean isRoleAccount() {
	return isRoleAccount;
}
/**
 * @param isRoleAccount the isRoleAccount to set
 */
public void setRoleAccount(boolean isRoleAccount) {
	this.isRoleAccount = isRoleAccount;
}
/**
 * @return the isFreeEmail
 */
public boolean isFreeEmail() {
	return isFreeEmail;
}
/**
 * @param isFreeEmail the isFreeEmail to set
 */
public void setFreeEmail(boolean isFreeEmail) {
	this.isFreeEmail = isFreeEmail;
}
public MailAttributes(boolean isSyntaxValid, boolean dnsExists, boolean mxExists, boolean mailboxExists,
		boolean isCatchall, boolean dmarcExists, boolean spfExists, boolean domainCreatedRecently, boolean isSpam,
		boolean isDisposible, boolean isRoleAccount, boolean isFreeEmail) {
	super();
	this.isSyntaxValid = isSyntaxValid;
	this.dnsExists = dnsExists;
	this.mxExists = mxExists;
	this.mailboxExists = mailboxExists;
	this.isCatchall = isCatchall;
	this.dmarcExists = dmarcExists;
	this.spfExists = spfExists;
	this.domainCreatedRecently = domainCreatedRecently;
	this.isSpam = isSpam;
	this.isDisposible = isDisposible;
	this.isRoleAccount = isRoleAccount;
	this.isFreeEmail = isFreeEmail;
}
public MailAttributes() {
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "MailAttributes [isSyntaxValid=" + isSyntaxValid + ", dnsExists=" + dnsExists + ", mxExists=" + mxExists
			+ ", mailboxExists=" + mailboxExists + ", isCatchall=" + isCatchall + ", dmarcExists=" + dmarcExists
			+ ", spfExists=" + spfExists + ", domainCreatedRecently=" + domainCreatedRecently + ", isSpam=" + isSpam
			+ ", isDisposible=" + isDisposible + ", isRoleAccount=" + isRoleAccount + ", isFreeEmail=" + isFreeEmail
			+ "]";
}







}
