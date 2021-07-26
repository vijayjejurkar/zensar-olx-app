package com.app.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthenticatedException extends RuntimeException{

	private String msg;

	public UnAuthenticatedException() {
		super();
		this.msg = "";
	}

	public UnAuthenticatedException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return this.msg;
	}
}
