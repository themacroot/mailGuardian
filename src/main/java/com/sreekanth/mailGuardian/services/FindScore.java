package com.sreekanth.mailGuardian.services;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xbill.DNS.TextParseException;

import com.sreekanth.mailGuardian.models.RiskCalculateInput;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;
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

	public RiskCalculatedOutput findRiskScore(RiskCalculateInput ip) throws IllegalStateException, UnknownHostException, TextParseException {

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

			op.increaseScore(10);
			op.addRemarks("MX Record Exists");

		} else {

			op.decreaseScore(10);
			op.setRemarks("No MX Record Exists");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;

		}
		
		if (sv.doesDmarcRecordExist(ip.getDomain())) {

			op.increaseScore(10);
			op.addRemarks("DMARC Record Exists");

		} else {

			op.decreaseScore(10);
			op.setRemarks("No DMARC Record Exists");
			op.setStatus("Failed");
			op.setComments("Decline");
			return op;

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

		return op;
		// String result = trieSearchService.searchInTrieSpam(ip.getDomain());
		// System.out.println(result);

	}
}
