package it.clevercom.echo.common.exception.model;

/**
 * 
 * @author luca
 * @version 1.1.0
 * Exception class used to map record not found exception
 * 
 */

public class RecordNotFoundException extends EchoException {
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
