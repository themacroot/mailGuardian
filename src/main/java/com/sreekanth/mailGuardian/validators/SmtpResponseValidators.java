package com.sreekanth.mailGuardian.validators;


/**
 * A class to handle SMTP (Simple Mail Transfer Protocol) response codes and provide corresponding error messages.
 */
public class SmtpResponseValidators {

	 /**
     * Handles an SMTP response code and returns a corresponding error message.
     *
     * @param responseCode The SMTP response code to be handled.
     * @return A string representing the error message associated with the given response code.
     */
	public static String whatsTheStatus(int responseCode) {
		
	
		        String errorMessage;

		        switch (responseCode) {
		            case 200:
		                errorMessage = "OK: The request was successful.";
		                break;
		            case 211:
		                errorMessage = "System status, or system help reply.";
		                break;
		            case 214:
		                errorMessage = "Help message: A response to the HELP command that usually includes a link or URL to the FAQ page.";
		                break;
		            case 220:
		                errorMessage = "SMTP Service ready: The receiving server is ready for the next command.";
		                break;
		            case 221:
		                errorMessage = "Service closing transmission channel: The receiving server is closing the SMTP connection.";
		                break;
		            case 235:
		                errorMessage = "2.7.0 Authentication succeeded: The sending server�s authentication is successful.";
		                break;
		            case 250:
		                errorMessage = "Requested mail action okay, completed: Success! The email was delivered.";
		                break;
		            case 251:
		                errorMessage = "User not local; will forward to <forward-path>: The receiving server doesn�t recognize the recipient but will forward it to another email address.";
		                break;
		            case 252:
		                errorMessage = "Cannot VRFY user, but will accept message and attempt delivery: The receiving server doesn�t recognize the recipient but will try to deliver the email anyway.";
		                break;
		            case 354:
		                errorMessage = "Start mail input: The email �header� has been received, the server is now waiting for the �body� of the message.";
		                break;
		            case 421:
		                errorMessage = "Service not available, closing transmission channel: The server is not accepting connections. The client should retry after a specified time.";
		                break;
		            case 450:
		                errorMessage = "Requested mail action not taken: The server encountered a temporary error and cannot complete the requested action.";
		                break;
		            case 451:
		                errorMessage = "Requested action aborted: Local error in processing.";
		                break;
		            case 452:
		                errorMessage = "Requested action not taken: Insufficient system storage.";
		                break;
		            case 500:
		                errorMessage = "Syntax error, command unrecognized: The server encountered a syntax error in the client�s command.";
		                break;
		            case 501:
		                errorMessage = "Syntax error in parameters or arguments: The server received a command that was not recognized or is missing parameters.";
		                break;
		            case 502:
		                errorMessage = "Command not implemented: The server has not implemented the requested action.";
		                break;
		            case 503:
		                errorMessage = "Bad sequence of commands: The server has encountered a bad sequence of commands.";
		                break;
		            case 504:
		                errorMessage = "Command parameter not implemented: The server does not support the command parameter.";
		                break;
		            case 550:
		                errorMessage = "Requested action not taken: The server encountered a permanent error and cannot complete the requested action.";
		                break;
		            case 551:
		                errorMessage = "User not local; please try <forward-path>: The recipient address is not local and the server will forward it to another server.";
		                break;
		            case 552:
		                errorMessage = "Requested mail action aborted: Exceeded storage allocation.";
		                break;
		            case 553:
		                errorMessage = "Requested action not taken: Mailbox name not allowed.";
		                break;
		            case 554:
		                errorMessage = "Transaction failed: The server encountered a permanent failure while processing the email.";
		                break;
		            default:
		                errorMessage = "Unknown response code: " + responseCode;
		        }

		        return errorMessage;
		    
	}
	
	
	
}
