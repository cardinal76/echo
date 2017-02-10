package it.clevercom.echo.rd.model.dto.response;

import java.io.Serializable;
import java.util.HashMap;

public class DeleteResponseDTO implements Serializable {
	private static final long serialVersionUID = -8765614816174283563L;
	
	private HashMap<String, String> ids;
	private String entityName;
	private String oldValue;
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
