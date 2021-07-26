package com.app.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {InvalidAdvertiseException.class})
	public ResponseEntity<Object> hanldeStockError(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, 
				exception.toString(), 
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST
				, request);
	}
	
	@ExceptionHandler(value = {InvalidUserException.class})
	public ResponseEntity<Object> hanldeUserError(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, 
				exception.toString(), 
				new HttpHeaders(), 
				HttpStatus.NOT_FOUND
				, request);
	}
	
	@ExceptionHandler(value = {UnAuthenticatedException.class})
	public ResponseEntity<Object> hanldeUnAuthenticatedError(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, 
				exception.toString(), 
				new HttpHeaders(), 
				HttpStatus.UNAUTHORIZED
				, request);
	}
}
