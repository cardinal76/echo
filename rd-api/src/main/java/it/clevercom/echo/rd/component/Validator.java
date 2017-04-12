package it.clevercom.echo.rd.component;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;

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
		if ((!status.equals("*")) && (WorkStatusEnum.getInstanceFromCodeValue(status) == null)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workstatus"),
							WorkStatusEnum.enumValuesToString()));
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
}