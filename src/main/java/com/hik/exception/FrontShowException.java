package com.hik.exception;

/**
 * 如果你想让异常的message直接显示给前端，那就抛出此类异常
 *
 */
public class FrontShowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FrontShowException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FrontShowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FrontShowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FrontShowException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FrontShowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
