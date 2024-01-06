package com.sreekanth.mailGuardian.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.constants.FreeEmailDomains;
import com.sreekanth.mailGuardian.constants.RoleBasedEmailsList;
import com.sreekanth.mailGuardian.models.MailAttributes;
import com.sreekanth.mailGuardian.models.RiskCalculateInput;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;
import com.sreekanth.mailGuardian.models.SMTPConnectInput;
import com.sreekanth.mailGuardian.models.SMTPConnectOutput;
import com.sreekanth.mailGuardian.utils.RandomEmailGenerator;
import com.sreekanth.mailGuardian.validators.SMTPMail;
import com.sreekanth.mailGuardian.validators.ServerValidator;
import com.sreekanth.mailGuardian.validators.SyntaxValidator;

@Component
public class FindAttributes {

	TrieSearchService trieSearchService = null;
	Logger logger = LoggerFactory.getLogger("AUDIT");

	@Autowired
	public void TrieController(TrieSearchService trieSearchService) {
		this.trieSearchService = trieSearchService;
	}

	@Autowired
	ServerValidator serverValidator;

	MailAttributes ea = new MailAttributes();

	public MailAttributes findAttributes(RiskCalculateInput ip) throws Exception {
		try {
		logger.info("Starting validation of " + ip.getEmail() + " ~ " + ip.getTrace());
		if (SyntaxValidator.isEmailSyntaxValid(ip.getEmail())) {
			ea.setSyntaxValid(true);

			logger.info("Syntax is valid for " + ip.getEmail() + " ~ " + ip.getTrace());
		} else {
			logger.info("Stopping validation as syntax is invalid for " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setSyntaxValid(false);
			return ea;
		}

		if (serverValidator.doesDNSRecordExist(ip.getDomain())) {
			ea.setDnsExists(true);
			logger.info("DNS record exists for " + ip.getEmail() + " ~ " + ip.getTrace());
		} else {
			logger.info("DNS record doesnt exists for " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDnsExists(false);
		}

		if (RoleBasedEmailsList.getRoleBasedEmails().contains(ip.getEmail().split("@")[0])) {
			logger.info("Email flagged as  " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setRoleAccount(true);
		} else {
			logger.info("Email not flagged as " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setRoleAccount(false);
		}

		if (serverValidator.doesMXRecordExist(ip.getDomain())) {
			ea.setMxExists(true);
			SMTPConnectOutput smtpco = new SMTPConnectOutput();
			SMTPMail smtpMail = new SMTPMail();
			ArrayList mxList = null;
			if (FreeEmailDomains.getDomains().contains(ip.getDomain())) {
				logger.info("Free email Provider " + ip.getEmail() + " ~ " + ip.getTrace());
				ea.setFreeEmail(true);
				mxList = serverValidator.getMX(ip.getDomain().toString());
				SMTPConnectInput smtpci = new SMTPConnectInput(ip.getEmail(), ip.getDomain(), "themacroot@gmail.com",
						"gmail.com", mxList.get(0).toString(), ip.getTrace());
				smtpco = smtpMail.ConnecttoSMTP(smtpci);
				if (smtpco.isMailboxExists()) {
					logger.info("Mailbox exists " + ip.getEmail() + " ~ " + ip.getTrace());
					ea.setMailboxExists(true);
				} else {
					logger.info("Mailbox doesnt exist " + ip.getEmail() + " ~ " + ip.getTrace());
					ea.setMailboxExists(false);
				}
			} else {
				logger.info("Email Provider is not free " + ip.getEmail() + " ~ " + ip.getTrace());
				mxList = serverValidator.getMX(ip.getDomain().toString());
				SMTPConnectInput smtpci = new SMTPConnectInput(ip.getEmail(), ip.getDomain(), "themacroot@gmail.com",
						"gmail.com", mxList.get(0).toString(), ip.getTrace());
				smtpco = smtpMail.ConnecttoSMTP(smtpci);
				if (smtpco.isMailboxExists()) {
					logger.info("Mailbox exists " + ip.getEmail() + " ~ " + ip.getTrace());
					ea.setMailboxExists(true);
					smtpci.setAddress(RandomEmailGenerator.generateRandomEmail(7) + "@" + ip.getDomain());
					smtpco = smtpMail.ConnecttoSMTP(smtpci);
					if (smtpco.isMailboxExists()) {
						ea.setCatchall(true);
						logger.info("Mailbox is catch all " + ip.getEmail() + " ~ " + ip.getTrace());
					} else {
						logger.info("Mailbox is not catch all " + ip.getEmail() + " ~ " + ip.getTrace());
						ea.setCatchall(false);
					}
				}
			}
		} else {
			logger.info("Terminating as MX record doesnt exist " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setMxExists(false);
			return ea;
		}

		if (serverValidator.doesDmarcRecordExist(ip.getDomain())) {
			logger.info("DMARC record exist " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDmarcExists(true);
		} else {
			logger.info("DMARC record doesnt exist " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDmarcExists(false);
		}

		if (serverValidator.doesSPFRecordExist(ip.getDomain())) {
			logger.info("SPF record exist " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setSpfExists(true);
		} else {
			logger.info("SPF record doesnt exist " + ip.getEmail() + " ~ " + ip.getTrace());
			ea.setSpfExists(false);
		}

		if (serverValidator.creationbeforeXYears(ip.getDomain(), 4)) {
			logger.info("Domain created before 4 years "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDomainCreatedRecently(false);
		} else {
			logger.info("Domain created recently "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDomainCreatedRecently(true);
		}

		if (trieSearchService.searchInTrieSpam(ip.getDomain())) {
			logger.info("Domain is spam "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setSpam(true);
		} else {
			logger.info("Domain is not spam "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setSpam(false);
		}

		if (trieSearchService.searchInTrieBurner(ip.getDomain())) {
			logger.info("Domain is burner "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDisposible(true);
		} else {
			logger.info("Domain is not burner "+  ip.getEmail() + " ~ " + ip.getTrace());
			ea.setDisposible(false);
		}

		return ea;

	}
		
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}
}
