package com.piedpiper.platform.core.exception;

public class ForbiddenDelException extends RuntimeException {
	private static final long serialVersionUID = 8335164454108338833L;

	public ForbiddenDelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ForbiddenDelException(String s) {
		super(s);
	}
}
