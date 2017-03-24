package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;

public class FieldErrorDTO implements Serializable {
	private static final long serialVersionUID = -4876616987991538873L;
	
	private String field;
	private String message;
	
	/**
	 * @param field
	 * @param message
	 */
	public FieldErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
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
}
