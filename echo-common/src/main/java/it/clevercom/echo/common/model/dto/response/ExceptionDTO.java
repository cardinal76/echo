package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;

public class ExceptionDTO implements Serializable {
	private static final long serialVersionUID = -9063362531689693073L;
	private String message;
	
	public ExceptionDTO(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}