/*
 * This class, SMTPMail, provides functionality for connecting to SMTP servers and validating email addresses.
 * It utilizes external libraries and utilities for DNS resolution and HTTP requests.
 */
package com.sreekanth.mailGuardian.validators;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sreekanth.mailGuardian.models.SMTPConnectInput;
import com.sreekanth.mailGuardian.models.SMTPConnectOutput;
import com.sreekanth.mailGuardian.utils.HttpUtils;
import com.sreekanth.mailGuardian.utils.YahooCheck;
import com.sreekanth.mailGuardian.constants.Literals;
import com.sreekanth.mailGuardian.constants.Logging;


public final class SMTPMail {

	// Logger for auditing purposes
	static Logger logger = LoggerFactory.getLogger(Literals.AUDIT_LOGGER);

	/*
	 * Connects to the SMTP server, validates email address, and returns the
	 * SMTPConnectOutput.
	 */
	public  SMTPConnectOutput ConnecttoSMTP(SMTPConnectInput ip) {
		HttpUtils httpUtils = new HttpUtils();
		SMTPConnectOutput smtpCO = new SMTPConnectOutput();

		try {

			// Check if the domain contains "yahoo" for separate validation
			if (ip.getDomain().contains(Literals.YAHOO_DOMAIN_IDENTIFIER)) {
				logger.info(Logging.DOMAIN_YAHOO + Logging.TRACE_SEPARATOR + ip.getTrace());
				String uname[] = ip.getAddress().split("@");
				if (YahooCheck.checkYahooUserExists(uname[0], ip.getTrace())) {
					smtpCO.setStatus(Literals.SUCCESS);
					smtpCO.setAction(Literals.TERMINATE);
					smtpCO.setServerResponse(Literals.MAILBOX_EXIST_LOG);
					smtpCO.setMailboxExists(true);
					logger.info(smtpCO.toString() + Logging.FOR_THE_ADD + ip.getAddress() + Logging.TRACE_SEPARATOR
							+ ip.getTrace());
					return smtpCO;
				} else {
					smtpCO.setStatus(Literals.FAILURE);
					smtpCO.setAction(Literals.TERMINATE);
					smtpCO.setServerResponse(Literals.MAILBOX_DOES_NOT_EXIST_LOG);
					smtpCO.setMailboxExists(false);
					logger.info(smtpCO.toString() + Logging.FOR_THE_ADD + ip.getAddress() + Logging.TRACE_SEPARATOR
							+ ip.getTrace());
					return smtpCO;
				}
			} else {
				int response;

				// Log opening connection details
				logger.info("Connecting to " + ip.getAddress() + " at domain " + ip.getDomain()
						+ Logging.TRACE_SEPARATOR + ip.getTrace());
				Socket socket = new Socket(ip.getMxAddr(), 25);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				// Log connection response
				response = httpUtils.hear(reader);
				logger.info(Logging.INVALID_RESPONSE_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());

				// Handle invalid response
				if (response != 220) {
					smtpCO.setServerResponse(Literals.INVALID_RESPONSE_LOG);
					reader.close();
					writer.close();
					socket.close();
					logger.info(Logging.STOPPING_VALIDATIONS_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
					throw new Exception(Literals.INVALID_RESPONSE_EXCEPTION);
				}

				// Log sending EHLO
				logger.info(Logging.EHLO_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
				httpUtils.say(writer, "EHLO " + ip.getFromDomain());
				response = httpUtils.hear(reader);
				logger.info(Logging.INVALID_RESPONSE_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());

				// Handle invalid response
				if (response != 250) {
					smtpCO.setServerResponse(Literals.INVALID_RESPONSE_LOG);
					reader.close();
					writer.close();
					socket.close();
					logger.info(Logging.STOPPING_VALIDATIONS_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
					throw new Exception(Literals.INVALID_RESPONSE_EXCEPTION);
				}

				// Log sending Sender Email
				logger.info(Logging.MAIL_FROM_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
				httpUtils.say(writer, "MAIL FROM: <" + ip.getFromEmail() + ">");
				response = httpUtils.hear(reader);
				logger.info(Logging.INVALID_RESPONSE_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());

				// Handle sender rejection
				if (response != 250) {
					smtpCO.setServerResponse(Literals.INVALID_RESPONSE_LOG);
					reader.close();
					writer.close();
					socket.close();
					logger.info(Logging.STOPPING_VALIDATIONS_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
					throw new Exception(Literals.SENDER_REJECTED_EXCEPTION);
				}

				// Log sending Receiver Email
				logger.info(Logging.RCPT_TO_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());
				httpUtils.say(writer, "RCPT TO: <" + ip.getAddress() + ">");
				response = httpUtils.hear(reader);
				logger.info(Logging.INVALID_RESPONSE_LOG + Logging.TRACE_SEPARATOR + ip.getTrace());

				// Resetting and Closing Connections
				httpUtils.say(writer, Literals.RSET_COMMAND);
				httpUtils.hear(reader);
				httpUtils.say(writer, Literals.QUIT_COMMAND);
				httpUtils.hear(reader);

				reader.close();
				writer.close();
				socket.close();

				// Handle mailbox does not exist
				if (response == 550) {
					smtpCO.setMailboxExists(false);
					smtpCO.setStatus(Literals.FAILURE);
					smtpCO.setAction(Literals.TERMINATE);
					smtpCO.setServerResponse(Literals.MAILBOX_DOES_NOT_EXIST_LOG);
					logger.info(smtpCO.toString() + Logging.FOR_THE_ADD+ ip.getAddress()+ Logging.TRACE_SEPARATOR + ip.getTrace());
					return smtpCO;
				}

				// Handle mailbox exists
				if (response == 250) {
					smtpCO.setMailboxExists(true);
					smtpCO.setStatus(Literals.SUCCESS);
					smtpCO.setAction(Literals.TERMINATE);
					smtpCO.setServerResponse(Literals.MAILBOX_EXIST_LOG);
					logger.info(smtpCO.toString() + Logging.FOR_THE_ADD + ip.getAddress()+ Logging.TRACE_SEPARATOR + ip.getTrace());
				} else {
					// Handle not able to verify mailbox
					smtpCO.setMailboxExists(true);
					smtpCO.setStatus(Literals.SUCCESS);
					smtpCO.setAction(Literals.TERMINATE);
					smtpCO.setServerResponse(Literals.MAILBOX_VERFY_ERR_LOG);
					logger.info(smtpCO.toString() +  Logging.FOR_THE_ADD+ ip.getAddress()+ Logging.TRACE_SEPARATOR + ip.getTrace());
				}
			}
		} catch (Exception e) {
			// Handle remote mail validation error
			smtpCO.setStatus(Literals.FAILURE);
			smtpCO.setMailboxExists(true);
			smtpCO.setAction("Proceed");
			logger.info(smtpCO.toString() +  Logging.FOR_THE_ADD + ip.getAddress()+ Logging.TRACE_SEPARATOR + ip.getTrace());
			logger.info("Mailbox validation error, accepting email"
					+ ip.getAddress() + Logging.TRACE_SEPARATOR + ip.getTrace());
		}

		return smtpCO;
	}
}