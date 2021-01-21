package com.mypractice.sb.CabServiceSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mypractice.sb.CabServiceSystem.bo.GenericErrorResponse;

@ControllerAdvice
public class CBSExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<GenericErrorResponse> handleException(Exception ex){
		
		GenericErrorResponse errResponse = new GenericErrorResponse();
		
		errResponse.setStatus("failure");
		errResponse.setReason(ex.getMessage());		
		
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
	

}
