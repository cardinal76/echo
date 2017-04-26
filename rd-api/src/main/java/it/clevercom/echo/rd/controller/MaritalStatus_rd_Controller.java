package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.MaritalStatusDTO;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
import it.clevercom.echo.rd.repository.IMaritalStatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/maritalstatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Get maritalstatus
 * @author luca
 */

public class MaritalStatus_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IMaritalStatus_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "Maritalstatus";
	private static String entity_id = "idmaritalstatus";
	
	/**
	 * Get marital status by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody MaritalStatusDTO get(@PathVariable Long id) throws Exception {
		Maritalstatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, MaritalStatusDTO.class);
	}
	
	/**
	 * Get marital status by criteria with pagination
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MaritalStatusDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idmaritalstatus", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IMaritalStatus_rd_Repository, Maritalstatus, MaritalStatusDTO> rp = 
				new CriteriaRequestProcessor<IMaritalStatus_rd_Repository, Maritalstatus, MaritalStatusDTO>(repo, 
						rdDozerMapper, 
						MaritalStatusDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// process data request
		return rp.process();
	}
	
	/**
	 * Add marital status
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String add(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.POST.toString(), MaritalStatus_rd_Controller.entity_name);
	}
	
	/**
	 * Update marital status
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String update(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.PUT.toString(), MaritalStatus_rd_Controller.entity_name);
	}
	
	/**
	 * Delete marital status
	 * @param maritalStatus
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), MaritalStatus_rd_Controller.entity_name);
	}
}
