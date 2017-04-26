package it.clevercom.echo.rd.component;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


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
}