package com.sreekanth.mailGuardian.validators;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import com.sreekanth.mailGuardian.models.SMTPConnectInput;
import com.sreekanth.mailGuardian.models.SMTPConnectOutput;

class SMTPMailTest {

	ServerValidator sv = new ServerValidator();
	
	  @Test
	    public void testConnecttoSMTP_SuccessfulConnection() throws IOException {
	        // Arrange
		  
		  ArrayList mxList = null;
		  SMTPMail nm = new SMTPMail();
		  SMTPConnectOutput actualOutput  = new SMTPConnectOutput();
				try {
					mxList = sv.getMX("sib.co.in");
					SMTPConnectInput input = new SMTPConnectInput("chatbot@gmail.com", "gmail.com", "themacroot@gmail.com","gmail.com",mxList.get(0).toString(),"sfdsfsdf");
					actualOutput = nm.ConnecttoSMTP(input);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     
	        SMTPConnectOutput expectedOutput = new SMTPConnectOutput();
	        expectedOutput.setStatus("Success");
	        expectedOutput.setAction("Terminate");
	        expectedOutput.setServerResponse("Success");


	        // Assert
	        assertEquals(expectedOutput, actualOutput);
	    }

	}
