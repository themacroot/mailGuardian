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

//	private TrieSearchService trieSearchService = null;
	@Autowired
	private FindScore calculator;
	private static Logger logger = LoggerFactory.getLogger("AUDIT");

//	@Autowired
//	public void TrieController(TrieSearchService trieSearchService) {
//		this.trieSearchService = trieSearchService;
//	}

	@GetMapping(value = "/email/{id}", produces = "application/json")
	public @ResponseBody RiskCalculatedOutput getScore(HttpServletRequest req, @PathVariable String id) throws Exception {

		logger.info("Request from IP --> " + req.getRemoteAddr() + "| With session --> " + req.getSession().getId()
				+ "| Request body --> " + id);

		String domain = StringUtils.substringAfterLast(id, "@").trim();
		logger.info("domain is "+domain);
		logger.info("mail is "+ id);
	
		RiskCalculatedOutput op = calculator
				.findRiskScore(new RiskCalculateInput(id, domain, req.getSession().getId()));

		logger.info("Request from IP --> " + req.getRemoteAddr() + "| With session --> " + req.getSession().getId()
				+ "| Response body --> " + op.toString());
		return op;
	}

}
