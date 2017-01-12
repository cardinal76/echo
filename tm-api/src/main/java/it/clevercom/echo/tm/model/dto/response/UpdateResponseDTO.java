package it.clevercom.echo.tm.model.dto.response;

import java.io.Serializable;
import java.util.HashMap;

public class UpdateResponseDTO implements Serializable {
	private static final long serialVersionUID = 5586951918496442774L;

	private HashMap<String, String> ids;
	private String entityName;
	private String oldValue;
	private String newValue;
	private String statusCode;
	private String message;
	
	/**
	 * @return the ids
	 */
	public HashMap<String, String> getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(HashMap<String, String> ids) {
		this.ids = ids;
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
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}
	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}
	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
