package it.clevercom.echo.common.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.clevercom.echo.common.exception.model.BadRequestException;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Global exception handler working for all methods of @Controller annotated classes
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Maps {@link BadRequestException} to a BAD_REQUEST http status
	 * @param e exception to handle 
	 * @return simple error message
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleBadRequestException(BadRequestException e) {
		logger.error("BadRequestException occurred : ", e);
		return e.getMessage();
	}
}
