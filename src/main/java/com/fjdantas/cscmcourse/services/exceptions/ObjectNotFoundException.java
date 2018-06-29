package com.fjdantas.cscmcourse.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	public static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	/*
	 * constructor that receives the exception message and 
	 * another exception with cause of error what occurred before
	 */
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
