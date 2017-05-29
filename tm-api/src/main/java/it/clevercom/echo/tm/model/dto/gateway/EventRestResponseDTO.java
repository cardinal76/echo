package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

/**
 * @author gfares
 * @since 0.1
 * 
 * DTO used to send response to gateway
 */
public class EventRestResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final boolean failed;
	
	private final String message;
	
	private EventRestResponseDTO(boolean failed, String message) {
		this.failed = failed;
		this.message = message;
	}
	
	/**
	 * @return A success response to the caller
	 */
	public static EventRestResponseDTO createSuccess() {
		return new EventRestResponseDTO(false, null);
	}
	
	/**
	 * @param message Error message
	 * @return A failed response to the caller with message
	 */
	public static EventRestResponseDTO createFailed(String message) {
		return new EventRestResponseDTO(true, message);
	}
	
	/**
	 * @param ex Exception to parse
	 * @return A failed response to the caller with the exception message
	 */
	public static EventRestResponseDTO createFailed(Exception ex) {
		return new EventRestResponseDTO(true, ex.getMessage());
	}

	/**
	 * @return the failed
	 */
	public boolean isFailed() {
		return failed;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
}
