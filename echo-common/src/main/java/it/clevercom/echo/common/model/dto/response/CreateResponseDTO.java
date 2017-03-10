package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;
import java.util.List;

public class CreateResponseDTO<T> implements Serializable {
	private static final long serialVersionUID = 6623502706488958072L;
	
	private String entityName;
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
