package com.mycare.actions.utils;

public class MYException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5009682577015592522L;

	public MYException() {		
	}

	public MYException(String message) {
		super(message);		
	}

	public MYException(Throwable cause) {
		super(cause);		
	}

	public MYException(String message, Throwable cause) {
		super(message, cause);		
	}

	public MYException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

}
