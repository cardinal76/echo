package it.clevercom.echo.common.exception.handler;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.dozer.MappingException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.exception.model.ValidationException;
import it.clevercom.echo.common.model.dto.response.ExceptionDTO;
import it.clevercom.echo.common.model.dto.response.ValidationExceptionDTO;

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
	 * @return exception dto message
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
	 * Maps {@link PageNotFoundException} to a NOT_FOUND http status
	 * @param e exception to handle 
	 * @return exception dto message
	 */
	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionDTO handlePageNotFoundException(PageNotFoundException e) {
		logger.warn(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.search.nopage"), e.getEntityName(), e.getPage()));
		return dto;
	}
	
	/**
	 * Maps {@link MappingException} to a INTERNAL_SERVER_ERROR http status
	 * @param e exception to handle 
	 * @return exception dto message
	 */
	@ExceptionHandler(MappingException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionDTO handleMappingException(MappingException e) {
		logger.fatal(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(env.getProperty("echo.api.exception.dozer.mapping"));
		return dto;
	}
	
	/**
	 * Maps {@link TransientPropertyValueException} to a FORBIDDEN http status
	 * @param e exception to handle 
	 * @return exception dto message
	 */
	@ExceptionHandler(TransientPropertyValueException.class)
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	public @ResponseBody ExceptionDTO handleTransientPropertyValueException(TransientPropertyValueException e) {
		logger.fatal(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(env.getProperty("echo.api.exception.hibernate.unsaved.transient.property"));
		return dto;
	}
	
	/**
	 * Maps {@link DataIntegrityViolationException} to a FORBIDDEN http status
	 * @param e exception to handle 
	 * @return exception dto message
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	public @ResponseBody ExceptionDTO handleConstraintViolationException(DataIntegrityViolationException e) {
		logger.fatal(e.getMessage(), e);
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(env.getProperty("echo.api.exception.hibernate.constraint.violation"));
		return dto;
	}
	
	/**
	 * Maps {@link ValidationException} to a BAD_REQUEST http status
	 * @param e exception to handle 
	 * @return exception dto message
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody ValidationExceptionDTO handleValidationException(ValidationException e) {
		logger.error(e.getMessage(), e);
		return e.getExceptions();
	}
	
//	/**
//	 * Maps {@link Exception} to a INTERNAL_SERVER_ERROR http status
//	 * @param e exception to handle 
//	 * @return exception dto message
//	 */
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	public @ResponseBody ExceptionDTO handleException(Exception e) {
//		logger.fatal(e.getMessage(), e);
//		ExceptionDTO dto = new ExceptionDTO();
//		dto.setMessage(env.getProperty("echo.api.exception.message"));
//		return dto;
//	}
	
}
