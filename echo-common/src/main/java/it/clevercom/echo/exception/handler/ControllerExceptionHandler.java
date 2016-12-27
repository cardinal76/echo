package it.clevercom.echo.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.clevercom.echo.exception.model.BadRequestException;

@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleBadRequestException(BadRequestException e) {
		logger.error("BadRequestException occurred : ", e);
		return e.getMessage();
	}
}
