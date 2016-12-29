package it.clevercom.echo.common.exception.model;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Exception class used to map badRequest validation errors
 *
 */
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
