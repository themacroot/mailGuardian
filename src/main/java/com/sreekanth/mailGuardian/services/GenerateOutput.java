package com.sreekanth.mailGuardian.services;



import com.sreekanth.mailGuardian.handler.ApplicationException;
import com.sreekanth.mailGuardian.handler.MailBoxDoesntExist;
import com.sreekanth.mailGuardian.models.MailAttributes;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;


public class GenerateOutput {

	static int score = 0;
	String reputation;
	static String remarks;

	public  RiskCalculatedOutput GenerateOutput(MailAttributes mailAttributes) throws Exception {
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

	private  int calculateScore(MailAttributes mailAttributes) throws Exception {

		int score = 0;
		// Syntax validation
		if (mailAttributes.isSyntaxValid()) {
			score += 5;
		}else {
			throw new ApplicationException()  ;
		}
		if(mailAttributes.isMailboxExists() == null) {
			score += 10;
			remarks = "Mailbox existance cannot be verified.";
		}
		else if (mailAttributes.isMailboxExists()) {
			score += 20; // Assigning a negative score for non-existent mailboxes
		}else {
			throw new MailBoxDoesntExist()  ;
		}

		// DNS and MX existence
		if (mailAttributes.isDnsExists() && mailAttributes.isMxExists()) {
			score += 15;
		}else if ( mailAttributes.isMxExists()) {
			score += 5;
		}
		
		if (mailAttributes.isCatchall()) {
			score -= 5;
			remarks = "Mail Provider catches all emails.";
		}

		// Presence of DMARC or SPF records
		if (mailAttributes.isDmarcExists() && mailAttributes.isSpfExists()) {
			score += 10;
		}else if(mailAttributes.isSpfExists()) {
			score +=5;
		}

		// Domain created recently
		if (mailAttributes.isDomainCreatedRecently()) {
			score -= 5;
			remarks += "Domain is created recently which can indicate a disposible Domain.";
		}else {
			score +=5;
		}

		// Negative factors
		if (mailAttributes.isRoleAccount()) {
			remarks += "The email is flagged as a role account.";
			score -= 5; // Assigning a negative score for role accounts
		}

		
		if (mailAttributes.isFreeEmail()) {
			score += 5; // Assigning a negative score for role accounts
		}
		if (mailAttributes.isDisposible()) {
			remarks += "The email is flagged as a disposible email.";
			score -= 15; // Assigning a negative score for disposable emails
		}else {
			score +=10;
		}


		if (mailAttributes.isSpam()) {
			score -= 15; // Assigning a negative score for spam emails
		}else {
			score +=10;
		}

		// Add more scoring rules as needed

		return score;
	}

	private  String categorizeRisk(int score) {
		// Define your risk categories based on the score
		if (score >= 75) {
			return "Low Risk";
		} else if (score >= 50 && score < 75) {
			return "Risky";
		} else {
			return "Reject";
		}
	}

	private  String setRemarks(MailAttributes mailAttributes) {

		return remarks;
	}

}
