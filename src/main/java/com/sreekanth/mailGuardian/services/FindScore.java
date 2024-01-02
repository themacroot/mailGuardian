package com.sreekanth.mailGuardian.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.models.RiskCalculateInput;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;
import com.sreekanth.mailGuardian.models.SMTPConnectInput;
import com.sreekanth.mailGuardian.models.SMTPConnectOutput;
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
	ServerValidator sv;

	RiskCalculatedOutput op = new RiskCalculatedOutput();

	public RiskCalculatedOutput findRiskScore(RiskCalculateInput ip) throws Exception {

		if (SyntaxValidator.isEmailSyntaxValid(ip.getEmail())) {
			op.setScore(10);
			op.setRemarks("Email Syntax is Valid");
		} else {
			op.setScore(0);
			op.setRemarks("Email Syntax is Invalid");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;
		}

		if (sv.doesDNSRecordExist(ip.getDomain())) {

			op.increaseScore(10);
			op.addRemarks("DNS exists for the Domain");

		} else {

			op.decreaseScore(0);
			op.addRemarks("DNS doesnt exists for the Domain");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;

		}

		if (sv.doesMXRecordExist(ip.getDomain())) {
			SMTPConnectInput smtpci = new SMTPConnectInput();
			SMTPConnectOutput smtpco = new SMTPConnectOutput();
			op.increaseScore(10);
			op.addRemarks("MX Record Exists");

			ArrayList mxList = null;
			mxList = sv.getMX(ip.getDomain().toString());
			smtpci.setAddress(ip.getEmail());
			smtpci.setDomain(ip.getDomain());
			smtpci.setFromEmail("themacroot@gmail.com");
			smtpci.setFromDomain("gmail.com");
			smtpci.setMxAddr(mxList.get(0).toString());
			SMTPMail smtpMail = new SMTPMail();
			smtpco = smtpMail.ConnecttoSMTP(smtpci);
			op.increaseScore(10);
			op.addRemarks(smtpco.getServerResponse());

		} else {

			op.decreaseScore(10);
			op.addRemarks("No MX Record Exists");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;

		}

		if (sv.doesDmarcRecordExist(ip.getDomain())) {

			op.increaseScore(10);
			op.addRemarks("DMARC Record Exists");

		} else {

			op.decreaseScore(10);
			op.addRemarks("No DMARC Record Exists");
			return op;

		}

		if (sv.doesSPFRecordExist(ip.getDomain())) {

			op.increaseScore(10);
			op.addRemarks("SPFR Record Exists");

		} else {

			op.decreaseScore(10);
			op.addRemarks("No SPFR Record Exists");

		}
		String tld[] = ip.getDomain().toString().split("\\.");
		System.out.println(tld[1]);
		if (tld[1].equals("com") || tld[1].equals("edu")) {
			if (sv.creationbeforeXYears(ip.getDomain(), 4)) {

				op.increaseScore(10);
				op.addRemarks("Domain created before 4 years");

			} else {

				op.decreaseScore(10);
				op.addRemarks("Domain Creted within 4 Years, chances of burner bomain");
				return op;

			}
		}

		if (trieSearchService.searchInTrieSpam(ip.getDomain())) {

			op.decreaseScore(10);
			op.addRemarks("Domain flagged spam");
		} else {

			op.increaseScore(10);
			op.addRemarks("Not spam");
		}

		if (!trieSearchService.searchInTrieBurner(ip.getDomain())) {
			op.increaseScore(10);
			op.addRemarks("Not a disposible mail");
		} else {
			op.decreaseScore(20);
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
