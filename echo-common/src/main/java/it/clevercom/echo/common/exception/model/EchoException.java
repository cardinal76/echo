package it.clevercom.echo.common.exception.model;

public class EchoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static String echoMessagePrefix = ""; 
	
	public EchoException(String message) {
		super(echoMessagePrefix + message);
	}

	public EchoException(String message, Throwable cause) {
		super(echoMessagePrefix + message, cause);
	}
}
