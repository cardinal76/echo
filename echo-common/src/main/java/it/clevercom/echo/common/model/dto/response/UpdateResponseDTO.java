package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;
import java.util.List;

public class UpdateResponseDTO<T> implements Serializable {
	private static final long serialVersionUID = 5586951918496442774L;

	private String entityName;
	private List<T> oldValue;
	private List<T> newValue;
	private String message;
	
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
	public List<T> getOldValue() {
		return oldValue;
	}
	
	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(List<T> oldValue) {
		this.oldValue = oldValue;
	}
	
	/**
	 * @return the newValue
	 */
	public List<T> getNewValue() {
		return newValue;
	}
	
	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(List<T> newValue) {
		this.newValue = newValue;
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
