package com.springboot.aop.exception;

public class MinimumAmountException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MinimumAmountException(String message) {
		super(message);
	}

}
