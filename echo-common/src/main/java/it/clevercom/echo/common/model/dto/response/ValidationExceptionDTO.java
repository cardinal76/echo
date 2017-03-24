package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationExceptionDTO implements Serializable {
	private static final long serialVersionUID = 5043060582398616066L;
	
	private String message;
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    
	/**
	 * 
	 */
	public ValidationExceptionDTO() {
		super();
	}

	/**
	 * @param message
	 */
	public ValidationExceptionDTO(String message) {
		super();
		this.message = message;
	}
 
    /**
     * @param field
     * @param message
     */
    public void addFieldError(String field, String message) {
    	FieldErrorDTO error = new FieldErrorDTO(field, message);
    	fieldErrors.add(error);
    	this.message = message;
    }

	/**
	 * @return the fieldErrors
	 */
	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * @param fieldErrors the fieldErrors to set
	 */
	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
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
