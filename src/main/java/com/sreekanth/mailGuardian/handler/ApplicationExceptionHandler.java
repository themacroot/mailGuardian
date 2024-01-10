package com.sreekanth.mailGuardian.handler;


import java.time.LocalDateTime;


import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sreekanth.mailGuardian.models.ApiError;







@ControllerAdvice
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	 @ExceptionHandler(ApplicationException.class)
	    public ResponseEntity<?> handleApplicationException(
	            final ApplicationException exception, final HttpServletRequest request){
	
	        ApiError response = new ApiError("You Messed Up! The input Email is wrong or doesnt exist","Failed",LocalDateTime.now().toString());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(MailBoxDoesntExist.class)
	    public ResponseEntity<?> handleMailBoxDoesntExist(
	            final ApplicationException exception, final HttpServletRequest request){
	
	        ApiError response = new ApiError("Mailbox Doesnt Exist","Failed",LocalDateTime.now().toString());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	 
	 
	@ExceptionHandler({Exception.class})
	public final ResponseEntity<?> handleException( Exception ex) {
		var response = new ApiError("We Messed Up!","Failed",LocalDateTime.now().toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}