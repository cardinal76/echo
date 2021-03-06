package it.clevercom.echo.rd.component;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.NotAuthorizedException;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.model.dto.AppSettingDTO;

@Component
public class Validator {
	@Autowired
	private Environment env;
	
	public Validator() {
		super();
	}

	/**
	 * Validate work status string with work status enum  
	 * @param status
	 * @throws BadRequestException
	 */
	public void validateStatus(String status) throws BadRequestException {
		if ((!status.equals("*")) && (!status.contains("|")) && (WorkStatusEnum.getInstanceFromCodeValue(status) == null)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workstatus"),
							WorkStatusEnum.enumValuesToString()));
		} else if ((!status.equals("*")) && (status.contains("|"))) {
			String[] statusItems = StringUtils.split(status, "\\|");
			for (int i = 0; i < statusItems.length; i++) {
				if (WorkStatusEnum.getInstanceFromCodeValue(statusItems[i]) == null) {
					throw new BadRequestException(
							MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
									env.getProperty("echo.api.crud.fields.workstatus"),
									WorkStatusEnum.enumValuesToString()));
				}
			}
		}
	}

	/**
	 * Validate work priority string with work priority enum  
	 * @param priority
	 * @throws BadRequestException
	 */
	public void validatePriority(String priority) throws BadRequestException {
		if ((!priority.equals("*")) && (WorkPriorityEnum.getInstanceFromCodeValue(priority) == null)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workpriority"),
							WorkPriorityEnum.enumValuesToString()));
		}		
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
	 * Validate update auth 
	 * @param user
	 * @param dto
	 * @throws NotAuthorizedException
	 */
	public void validateUsername (String user, Object dto) throws NotAuthorizedException {
		if (dto instanceof AppSettingDTO) {
			AppSettingDTO parsedDTO = (AppSettingDTO) dto;
			if ((StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(parsedDTO.getUser())) && (!user.equals(parsedDTO.getUser()))) {
				throw new NotAuthorizedException(env.getProperty("echo.api.crud.validation.cannotupdate.duetoinvalidauth"));
			}
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