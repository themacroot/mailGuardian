package com.sreekanth.mailGuardian.handler;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sreekanth.mailGuardian.models.RiskCalculatedOutput;




@ControllerAdvice
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {



	@ExceptionHandler({Exception.class})
	public final ResponseEntity<?> handleException( Exception ex) {
		var response = new RiskCalculatedOutput("", 0, "Failed", "We messed up!");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}