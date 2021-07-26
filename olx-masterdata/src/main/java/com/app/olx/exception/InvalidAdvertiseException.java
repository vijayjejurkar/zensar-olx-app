package com.app.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseException extends RuntimeException{
	
	private String msg;

	public InvalidAdvertiseException() {
		super();
		this.msg = "";
	}

	public InvalidAdvertiseException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Invalid Stock Id: "+this.msg;
	}

}
