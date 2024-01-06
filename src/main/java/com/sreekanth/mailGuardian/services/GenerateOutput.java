package com.sreekanth.mailGuardian.services;

import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.handler.ApplicationException;
import com.sreekanth.mailGuardian.models.MailAttributes;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;

@Component
public class GenerateOutput {

	int score = 0;
	String reputation;
	String remarks;

	public static RiskCalculatedOutput GenerateOutput(MailAttributes mailAttributes) throws Exception {
		RiskCalculatedOutput apiResponse = new RiskCalculatedOutput();
		try {
			int score = calculateScore(mailAttributes);
			String reputation = categorizeRisk(score);
			String remarks= setRemarks(mailAttributes);
			apiResponse.setScore(score);
			apiResponse.setRemarks(remarks);
			apiResponse.setReputation(reputation);
			
			apiResponse.setMa(mailAttributes);
			apiResponse.setStatus("Success");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	
		return apiResponse;
	}

	private static int calculateScore(MailAttributes mailAttributes) throws Exception {

		int score = 0;
		// Syntax validation
		if (mailAttributes.isSyntaxValid()) {
			score += 5;
		}else {
			throw new ApplicationException()  ;
		}

		// DNS and MX existence
		if (mailAttributes.isDnsExists() && mailAttributes.isMxExists()) {
			score += 10;
		}

		// Presence of DMARC or SPF records
		if (mailAttributes.isDmarcExists() && mailAttributes.isSpfExists()) {
			score += 8;
		}

		// Domain created recently
		if (mailAttributes.isDomainCreatedRecently()) {
			score -= 10;
		}

		// Negative factors
		if (mailAttributes.isRoleAccount()) {
			score -= 10; // Assigning a negative score for role accounts
		}

		if (mailAttributes.isDisposible()) {
			score -= 15; // Assigning a negative score for disposable emails
		}

		if (mailAttributes.isSpam()) {
			score -= 15; // Assigning a negative score for spam emails
		}

		if (mailAttributes.isMailboxExists()) {
			score -= 20; // Assigning a negative score for non-existent mailboxes
		}

		// Add more scoring rules as needed

		return score;
	}

	private static String categorizeRisk(int score) {
		// Define your risk categories based on the score
		if (score >= 0 && score < 10) {
			return "Low Risk";
		} else if (score >= 10 && score < 20) {
			return "Moderate Risk";
		} else {
			return "High Risk";
		}
	}

	private static String setRemarks(MailAttributes mailAttributes) {

		return "remarks";
	}

}
