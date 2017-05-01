package it.clevercom.echo.common.exception.model;

/**
 * 
 * @author luca
 * @version 1.1.0
 * Exception class used to map cannot deactivate exception
 * 
 */

public class DeactivateException extends EchoException {
	private static final long serialVersionUID = 1L;
	
	private String entityName;
	private String param;
	private String value;
	
	/**
	 * @param message
	 */
	public DeactivateException(String message) {
		super(message);
	}

	/**
	 * @param entityName
	 * @param param
	 * @param value
	 */
	public DeactivateException(String entityName, String param, String value) {
		super("cannot deactivate record");
		this.entityName = entityName;
		this.param = param;
		this.value = value;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DeactivateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
