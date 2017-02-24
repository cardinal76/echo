package it.clevercom.echo.common.exception.handler;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.model.dto.response.ExceptionDTO;

/**
 * 
 * @author alx
 * @author luca
 * @since 28/12/2016
 * Global exception handler working for all methods of @Controller annotated classes
 *
 */
@ControllerAdvice
@PropertySource("classpath:rest.platform.properties")

public class ControllerExceptionHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private Environment env;
	
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
	
	/**
	 * Maps {@link RecordNotFoundException} to a NOT_FOUND http status
	 * @param e exception to handle 
	 * @return simple error message
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionDTO handleRecordNotFoundException(RecordNotFoundException e) {
		logger.warn(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), e.getEntityName(), e.getRecordId()));
		return dto;
	}
	
	/**
	 * Maps {@link Exception} to a NOT_FOUND http status
	 * @param e exception to handle 
	 * @return simple error message
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionDTO handleException(Exception e) {
		logger.fatal(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(env.getProperty("echo.api.exception.message"));
		return dto;
	}
}
