package com.sboot.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BillIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7028967364583903332L;

	public BillIdNotFoundException(String msg) {
		super(msg);
	}
}
