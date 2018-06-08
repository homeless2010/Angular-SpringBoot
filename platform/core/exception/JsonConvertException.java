package com.piedpiper.platform.core.exception;

public class JsonConvertException extends RuntimeException {
	private static final long serialVersionUID = 8335163454108338833L;

	public JsonConvertException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JsonConvertException(String s) {
		super(s);
	}
}
