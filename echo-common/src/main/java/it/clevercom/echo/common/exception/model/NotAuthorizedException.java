package it.clevercom.echo.common.exception.model;

/**
 * @author luca
 */

public class NotAuthorizedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException(String message) {
		super(message);
	}

	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
