package it.clevercom.echo.common.model.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExceptionDTO implements Serializable {
	private static final long serialVersionUID = -9063362531689693073L;
	private String message;
	private String controller;
	private String controllerMethod;
	private String httpMethod;
	private String params;
	private String stackTrace;
	
	public ExceptionDTO() {
		super();
	}
	
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
	 * @return the controller
	 */
	public String getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(String controller) {
		this.controller = controller;
	}

	/**
	 * @return the controllerMethod
	 */
	public String getControllerMethod() {
		return controllerMethod;
	}

	/**
	 * @param controllerMethod the controllerMethod to set
	 */
	public void setControllerMethod(String controllerMethod) {
		this.controllerMethod = controllerMethod;
	}

	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace the stackTrace to set
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}