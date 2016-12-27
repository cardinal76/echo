package it.clevercom.echo.exception.model;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
