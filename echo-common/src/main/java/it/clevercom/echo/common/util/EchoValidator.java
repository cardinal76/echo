package it.clevercom.echo.common.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.common.exception.model.BadRequestException;

/**
 * 
 * @author luca
 */

@Component
public class EchoValidator {
	@Autowired
	private Environment env;
	
	public EchoValidator() {
		super();
	}
	
	/**
	 * Validate sort param
	 * @param sort
	 * @throws BadRequestException
	 */
	public void validateSort(String sort) throws BadRequestException {
		if (!(sort.equalsIgnoreCase("asc") || sort.equalsIgnoreCase("desc"))) { 
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}		
	}
	
	/**
	 * 
	 * @param dto
	 * @throws BadRequestException 
	 */
	public void validateDTOIdd (AbstractEchoDTO dto, String entity_name) throws BadRequestException {
		// if an id is not present throw bad request
		if(dto.getIdd()==null) 
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
	}
	
	/**
	 * 
	 * @param dto
	 * @throws BadRequestException 
	 */
	public void validateDTONullIdd(AbstractEchoDTO dto, String entity_id) throws BadRequestException {
		// if an id is not present throw bad request
		if(dto.getIdd()!=null) 
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.valorized.id"), entity_id));
	}

	/**
	 * 
	 * @param id
	 * @param entity_name
	 * @throws BadRequestException
	 */
	public void validateId(Long id, String entity_name) throws BadRequestException {
		// if an id is not present throw bad request
		if ((id == null) || (id.longValue()<=0)) 
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
	}
	
	/**
	 * 
	 * @param field
	 * @param clazz
	 * @param entity_name
	 * @throws BadRequestException
	 */
	public void validateSortField(String field, Class<?> clazz, String entity_name) throws BadRequestException {
		// TODO Auto-generated method stub
		try {
			Field someField = clazz.getDeclaredField(field);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.crud.validation.invalidsortelement"), field, entity_name));
		}
	}
}
