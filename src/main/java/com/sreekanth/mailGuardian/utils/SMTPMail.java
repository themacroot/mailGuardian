package com.sreekanth.mailGuardian.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import com.sreekanth.mailGuardian.models.SMTPConnectInput;
import com.sreekanth.mailGuardian.models.SMTPConnectOutput;

import com.sreekanth.mailGuardian.enums.AddressStatus;

import com.sreekanth.mailGuardian.validators.ServerValidator;
import com.sreekanth.mailGuardian.validators.SmtpResponseValidators;

// Tool set for verifying email address using  DNS util from xbill. 
@SuppressWarnings("deprecation")
public final class SMTPMail {
	
	
	//(String) mxList.get(mx) == mxAddr
	public static SMTPConnectOutput ConnecttoSMTP(SMTPConnectInput ip) {
		HttpUtils hu = new HttpUtils();
		SMTPConnectOutput smtpCO = new SMTPConnectOutput();
		try {
			int res;

			System.out.println("Opening Connection with " + ip.getAddress() + " at domain " + ip.getDomain() + " ....");
			Socket skt = new Socket(ip.getMxAddr(), 25);
			BufferedReader rdr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			BufferedWriter wtr = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));
			res = hu.hear(rdr);
			System.out.println("Connection Response is " + res + " corresponds to "
					+ SmtpResponseValidators.whatsTheStatus(res) + " .");
			if (res != 220) {
				smtpCO.setServerResponse(SmtpResponseValidators.whatsTheStatus(res));
				rdr.close();
				wtr.close();
				skt.close();
				throw new Exception("Invalid header");
			}
			
			
			System.out.println("Sending EHLO (Extension Hello)  ....");
			hu.say(wtr, "EHLO " + ip.getFromDomain());
			res = hu.hear(rdr);
			System.out.println("Connection Response is " + res + " corresponds to "
					+ SmtpResponseValidators.whatsTheStatus(res) + " .");
			if (res != 250) {
				smtpCO.setServerResponse(SmtpResponseValidators.whatsTheStatus(res));
				rdr.close();
				wtr.close();
				skt.close();
				throw new Exception("Not ESMTP");
			}
			
			
			System.out.println("Sending Sender Email  ....");
			hu.say(wtr, "MAIL FROM: <" + ip.getFromEmail() + ">");
			res = hu.hear(rdr);
			System.out.println("Connection Response is " + res + " corresponds to "
					+ SmtpResponseValidators.whatsTheStatus(res) + " .");
			if (res != 250) {
				smtpCO.setServerResponse(SmtpResponseValidators.whatsTheStatus(res));
				rdr.close();
				wtr.close();
				skt.close();
				throw new Exception("Sender rejected");
			}
			
			
			System.out.println("Sending Reciever Email  ....");
			hu.say(wtr, "RCPT TO: <" + ip.getAddress() + ">");
			res = hu.hear(rdr);
			System.out.println("Connection Response is " + res + " corresponds to "
					+ SmtpResponseValidators.whatsTheStatus(res) + " .");

			
			System.out.println("Sending Verify Command  ....");
			hu.say(wtr, "VRFY " + ip.getFromDomain());
			res = hu.hear(rdr);
			System.out.println("Connection Response is " + res + " corresponds to "
					+ SmtpResponseValidators.whatsTheStatus(res) + " .");
			
			
			//Resetting and Closing Connections
			hu.say(wtr, "RSET");
			hu.hear(rdr);
			hu.say(wtr, "QUIT");
			hu.hear(rdr);

			rdr.close();
			wtr.close();
			skt.close();

			if (res == 550) {
				
				smtpCO.setStatus("Failure");
				smtpCO.setAction("Terminate");
				smtpCO.setServerResponse(SmtpResponseValidators.whatsTheStatus(res));
				System.out.println(smtpCO.toString()+" for the address " + ip.getAddress());
				return smtpCO;
			}
			
			smtpCO.setStatus("Success");
			smtpCO.setAction("Terminate");
			smtpCO.setServerResponse(SmtpResponseValidators.whatsTheStatus(res));
			System.out.println(smtpCO.toString()+" for the address " + ip.getAddress());

		} catch (Exception e) {
			
			smtpCO.setStatus("Failure");
			smtpCO.setAction("Proceed");
		
			System.out.println(smtpCO.toString()+" for the address " + ip.getAddress());
			System.out.println("[mail validation] remote mail validation error. Accepting email anyway: email="
					+ ip.getAddress() + " - " + e.getMessage());
		}
		return smtpCO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




    




 

}