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
	
	private String entityName;
	private String recordId;
	
	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(String entityName, String recordId) {
		super("no record has been found.");
		this.entityName = entityName;
		this.recordId = recordId;
	}

	public RecordNotFoundException(String message, Throwable cause) {
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
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
