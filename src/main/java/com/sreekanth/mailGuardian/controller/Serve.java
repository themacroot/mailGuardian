/*
 * This class represents the controller for handling requests related to email risk calculation.
 * It utilizes the Spring framework's RESTful web services capabilities.
 */

package com.sreekanth.mailGuardian.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sreekanth.mailGuardian.models.RiskCalculateInput;
import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;
import com.sreekanth.mailGuardian.services.FindScore;
import com.sreekanth.mailGuardian.services.TrieSearchService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class Serve {

	@Autowired
	private FindScore calculator;
	private static Logger logger = LoggerFactory.getLogger("AUDIT");

	/*
	 * Handles GET requests to /email/{id} endpoint, where {id} is the email
	 * address. Calculates and returns the risk score for the given email. Logs
	 * relevant information for auditing purposes.
	 */

	@GetMapping(value = "/email/{id}", produces = "application/json")
	public @ResponseBody RiskCalculatedOutput getScore(HttpServletRequest req, @PathVariable String id)
			throws Exception {

		// Log incoming request details
		logger.info("Request from IP --> " + req.getRemoteAddr() + "| With session --> " + req.getSession().getId()
				+ "| Request body --> " + id);
		// Extract domain from email address
		String domain = StringUtils.substringAfterLast(id, "@").trim();
		logger.info("domain is " + domain);
		logger.info("mail is " + id);

		// Calculate risk score using the injected FindScore service
		RiskCalculatedOutput op = calculator
				.findRiskScore(new RiskCalculateInput(id, domain, req.getSession().getId()));
		// Log outgoing response details
		logger.info("Request from IP --> " + req.getRemoteAddr() + "| With session --> " + req.getSession().getId()
				+ "| Response body --> " + op.toString());
		// Return the calculated risk score
		return op;
	}

}
