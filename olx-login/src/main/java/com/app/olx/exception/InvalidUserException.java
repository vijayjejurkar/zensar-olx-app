package com.app.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidUserException extends RuntimeException{
	
	private String msg;

	public InvalidUserException() {
		super();
		this.msg = "";
	}

	public InvalidUserException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Invalid user Id: "+this.msg;
	}

}
