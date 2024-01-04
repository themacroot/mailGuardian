package com.sreekanth.mailGuardian.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.constants.EmailDomains;
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
public class FindScore {

	TrieSearchService trieSearchService = null;
	Logger logger = LoggerFactory.getLogger("AUDIT");

	@Autowired
	public void TrieController(TrieSearchService trieSearchService) {
		this.trieSearchService = trieSearchService;
	}

	@Autowired
	ServerValidator serverValidator;

	MailAttributes ea = new MailAttributes();

	public MailAttributes findRiskScore(RiskCalculateInput ip) throws Exception {
		logger.info("Starting Validation of " + ip.getEmail() + " ~ " + ip.getTrace());
		if (SyntaxValidator.isEmailSyntaxValid(ip.getEmail())) {
			ea.setSyntaxValid(true);

			logger.info("Syntax is Valid for " + ip.getEmail() + " ~ " + ip.getTrace());
		} else {
			ea.setSyntaxValid(false);
			return ea;
		}

		if (serverValidator.doesDNSRecordExist(ip.getDomain())) {
			ea.setDnsExists(true);

		} else {
			ea.setDnsExists(false);
		}

		if (serverValidator.doesMXRecordExist(ip.getDomain())) {
			ea.setMxExists(true);
			SMTPConnectOutput smtpco = new SMTPConnectOutput();
			SMTPMail smtpMail = new SMTPMail();
			ArrayList mxList = null;
			if (EmailDomains.getDomains().contains(ip.getDomain())) {
				ea.setFreeEmail(true);
				mxList = serverValidator.getMX(ip.getDomain().toString());
				SMTPConnectInput smtpci = new SMTPConnectInput(ip.getEmail(), ip.getDomain(), "themacroot@gmail.com",
						"gmail.com", mxList.get(0).toString(), ip.getTrace());
				smtpco = smtpMail.ConnecttoSMTP(smtpci);
				if (smtpco.isMailboxExists()) {
					ea.setMailboxExists(true);
				} else {
					ea.setMailboxExists(false);
				}
			} else {
				mxList = serverValidator.getMX(ip.getDomain().toString());
				SMTPConnectInput smtpci = new SMTPConnectInput(ip.getEmail(), ip.getDomain(), "themacroot@gmail.com",
						"gmail.com", mxList.get(0).toString(), ip.getTrace());
				smtpco = smtpMail.ConnecttoSMTP(smtpci);
				if (smtpco.isMailboxExists()) {
					ea.setMailboxExists(true);
					smtpci.setAddress(RandomEmailGenerator.generateRandomEmail(7) + "@" + ip.getDomain());
					smtpco = smtpMail.ConnecttoSMTP(smtpci);
					if (smtpco.isMailboxExists())
						ea.setCatchall(true);
					ea.setCatchall(false);
				}
			}
		} else {
			ea.setMxExists(false);
			return ea;
		}

		if (serverValidator.doesDmarcRecordExist(ip.getDomain())) {
			ea.setDmarcExists(true);
		} else {
			ea.setDmarcExists(false);
		}
		if (serverValidator.doesSPFRecordExist(ip.getDomain())) {
			ea.setSpfExists(true);
		} else {
			ea.setSpfExists(false);
		}
		
		String tld[] = ip.getDomain().toString().split("\\.");
		System.out.println(tld[1]);
		if (tld[1].equals("com") || tld[1].equals("edu")) {
			if (serverValidator.creationbeforeXYears(ip.getDomain(), 4)) {

				op.increaseScore(10);
				op.addRemarks("Domain created before 4 years");

			} else {

				op.decrementScore(10);
				op.addRemarks("Domain Creted within 4 Years, chances of burner bomain");
				return op;

			}
		}

		if (trieSearchService.searchInTrieSpam(ip.getDomain())) {

			op.decrementScore(10);
			op.addRemarks("Domain flagged spam");
		} else {

			op.increaseScore(10);
			op.addRemarks("Not spam");
		}

		if (!trieSearchService.searchInTrieBurner(ip.getDomain())) {
			op.increaseScore(10);
			op.addRemarks("Not a disposible mail");
		} else {
			op.decrementScore(20);
			op.addRemarks("Disposible mail");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;
		}

		op.setStatus("Success");
		op.setComments("Success");
		return op;
		// String result = trieSearchService.searchInTrieSpam(ip.getDomain());
		// System.out.println(result);

	}
}
