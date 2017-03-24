package it.clevercom.echo.common.exception.model;

import it.clevercom.echo.common.model.dto.response.ValidationExceptionDTO;

/**
 * @author luca
 */

public class ValidationException extends EchoException {
	private static final long serialVersionUID = -1807489430685549437L;
	
	private ValidationExceptionDTO exceptions;
	
	/**
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
		exceptions = new ValidationExceptionDTO(message);
	}
	
	/**
	 * @param message
	 * @param exceptions
	 */
	public ValidationException(String message, ValidationExceptionDTO exceptions) {
		super(message);
		this.exceptions = exceptions;
		this.exceptions.setMessage(message);
	}

	/**
	 * @return the exceptions
	 */
	public ValidationExceptionDTO getExceptions() {
		return exceptions;
	}
	
	/**
	 * @param exceptions the exceptions to set
	 */
	public void setExceptions(ValidationExceptionDTO exceptions) {
		this.exceptions = exceptions;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
